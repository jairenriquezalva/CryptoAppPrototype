package com.example.crypto;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.crypto.domain.BlockStrategy;
import com.example.crypto.domain.EncryptionStrategy;
import com.example.crypto.domain.Encryptor;
import com.example.crypto.domain.TranspositionStrategy;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.crypto", appContext.getPackageName());
    }

    @Test
    public void blockEncryptionTest(){
        Encryptor enc = new Encryptor(new BlockStrategy(new char[]{'2','5','3','1'}));
        String result = enc.encrypt("hola");
        String Expected = "TOXG";
        assertEquals(Expected,result);
    }

    @Test
    public void transpositionEncryptionTest(){
        Encryptor enc = new Encryptor(new TranspositionStrategy(new char[]{'2','5','3','1'}));
        String result = enc.encrypt("hola");
        String Expected = "EOIB";
        assertEquals(Expected,result);
    }
}
