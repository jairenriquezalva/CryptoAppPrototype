package com.example.crypto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.crypto.domain.BlockStrategy;
import com.example.crypto.domain.Encryptor;
import com.example.crypto.domain.TranspositionStrategy;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EncryptFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EncryptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EncryptFragment extends Fragment {

    Encryptor encryptor;

    public EncryptFragment() {
        encryptor = new Encryptor();
    }

    public static EncryptFragment newInstance(String param1, String param2) {
        EncryptFragment fragment = new EncryptFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encrypt, container, false);
        Spinner encryptMethods = v.findViewById(R.id.encryptMethodsSpinner);
        Button encrypt = v.findViewById(R.id.btnEncrypt);
        final TextView txtPlainText = v.findViewById(R.id.txtPlainText);
        final TextView txtKeyword = v.findViewById(R.id.txtKeyword);
        final TextView txtEncryptedText = v.findViewById(R.id.txtEncryptedText);
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,new String[]{"Trama","Transposicion"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        encryptMethods.setAdapter(adapter);
        encryptMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position){
                    case 0:     encryptor.setStrategy(new BlockStrategy());
                        break;
                    case 1:     encryptor.setStrategy(new TranspositionStrategy());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char[] keyword = txtKeyword.getText().toString().toCharArray();
                String plainText = txtPlainText.getText().toString();
                encryptor.setKeyword(keyword);
                String encryptedText = encryptor.encrypt(plainText);
                txtEncryptedText.setText(encryptedText);
            }
        });
        return v;
    }


}
