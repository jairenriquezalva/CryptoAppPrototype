package com.example.crypto.domain;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TranspositionStrategy implements EncryptionStrategy {

    char[] keyword;
    char[] alphabet;

    public TranspositionStrategy() {
        this.keyword = new char[]{1,2,3,4};
        this.alphabet = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    }

    public TranspositionStrategy(char[] keyword, char[] alphabet){
        this.keyword = keyword;
        this.alphabet = alphabet;
    }

    public TranspositionStrategy( char[] keyword){
        this.keyword = keyword;
        this.alphabet = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    }

    public void setKeyword(char[] keyword) {
        this.keyword = keyword;
    }

    @Override
    public String encrypt(String plainText) {
        //prepare text to encrypt
        String preparedText = plainText.trim();
        preparedText = preparedText.toUpperCase();
        char[] preparedChar = preparedText.toCharArray();
        //prepare new alphabet
        int blocksLenght = alphabet.length/keyword.length+1;
        char[][] blocks = new char[blocksLenght][keyword.length];
        int counter = 0;
        for(int i = 0; i<blocksLenght; i++){
            for(int j = 0; j<keyword.length; j++){
                if(counter>=alphabet.length){
                    blocks[i][j] = 0;
                }else {
                    blocks[i][j] = alphabet[counter];
                }
                counter++;
            }
        }
        int nullChar=0;
        for (int i =0; i<blocks[blocksLenght-1].length; i++){
            if(blocks[blocksLenght-1][i]==0){
                nullChar++;
            }
        }
        char[] aux = blocks[blocksLenght-1].clone();
        for(int i =0;i<nullChar;i++){
            blocks[blocksLenght-1][i]=aux[keyword.length-i-1];
        }
        for(int i =0;i<keyword.length-nullChar;i++){
            blocks[blocksLenght-1][i+nullChar]=aux[i];
        }
        char[] orderedKeyword = keyword.clone();
        Arrays.sort(orderedKeyword);
        char[][] resultingBlocks = new char[blocksLenght][keyword.length];
        for (int i = 0; i<blocksLenght; i++){
            for(int j = 0; j<keyword.length; j++){
                int resultingIndex=0;
                for(int k =0; k<keyword.length; k++){
                    if(keyword[j]==orderedKeyword[k]){
                        resultingIndex=k;
                    }
                }
                resultingBlocks[i][j] = blocks[i][resultingIndex];
            }
        }
        int nullCounter = 0;
        counter = 0;
        char[] modifiedAlphabet = new char[alphabet.length];
        for(int i = 0; i<blocksLenght; i++){
            for(int j = 0; j<keyword.length; j++){
                if(resultingBlocks[i][j] != 0){
                    modifiedAlphabet[counter] = resultingBlocks[i][j];
                    counter++;
                }
            }
        }
        //remplace text
        char[] encryptedChar = new char[preparedChar.length];
        for (int i = 0; i<preparedChar.length; i++){
            char encryptedVal=0;
            for (int j = 0; j<alphabet.length; j++){
                if(preparedChar[i] == alphabet[j]){
                    encryptedVal=modifiedAlphabet[j];
                }
            }
            encryptedChar[i] = encryptedVal;
        }
        return new String(encryptedChar);
    }
}
