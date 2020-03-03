package com.example.crypto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static final int NUM_OF_FRAGMENTS = 3;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.view_pager);
        pagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public class FragmentAdapter extends FragmentStatePagerAdapter {

        public FragmentAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch(position){
                case 0:
                    fragment = new EncryptFragment();
                    break;
                case 1:
                    fragment = new DecryptFragment();
                    break;
                case 2:
                    fragment = new EncryptFragment();
                    break;
                default:
                    fragment = new EncryptFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
