package com.example.crypto.domain;

import java.util.Arrays;

public class BlockStrategy implements EncryptionStrategy {

    private char[] keyword;
    private char[] alphabet;

    public BlockStrategy() {
        this.keyword = new char[]{1,2,3,4};
        this.alphabet = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    }

    public BlockStrategy(char[] keyword, char[] alphabet){
        this.keyword = keyword;
        this.alphabet = alphabet;
    }

    public BlockStrategy( char[] keyword){
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
        int blockLenght = alphabet.length /keyword.length;
        int counter = 0;
        int blockCounter = 0;
        char[][] blockArray = new char[keyword.length+1][blockLenght];
        for(int i = 0; i < alphabet.length; i++){
            blockArray[blockCounter][counter] = alphabet[i];
            if(counter == blockLenght-1){
                counter = 0;
                blockCounter++;
            }
            else{
                counter++;
            }
        }
        char[] orderedKeyword = keyword.clone();
        Arrays.sort(orderedKeyword);
        int[] order = new int[orderedKeyword.length];
        for(int i = 0; i<keyword.length; i++){
            int position = -1;
            for(int j = 0;j<keyword.length;j++){
                if(keyword[j]==orderedKeyword[i]){
                    position = j;
                }
            }
            order[i]=position;
        }
        char[][] blockArrayOrdered = new char[keyword.length+1][blockLenght];
        char[] finalAlphabet= new char[alphabet.length];
        for(int i =0; i<keyword.length+1;i++){
            if(i==keyword.length){
                blockArrayOrdered[i]=blockArray[i];
            }else{
                blockArrayOrdered[order[i]]=blockArray[i];
            }
        }
        counter = 0;
        blockCounter = 0;
        for(int i=0; i<alphabet.length;i++){
            finalAlphabet[i] = blockArrayOrdered[blockCounter][counter];
            if(counter==blockLenght-1){
                counter = 0;
                blockCounter++;
            }else{
                counter++;
            }
        }
        //remplace text
        char[] encryptedChar = new char[preparedChar.length];
        for (int i = 0; i<preparedChar.length; i++){
            char encryptedVal=0;
            for (int j = 0; j<alphabet.length; j++){
                if(preparedChar[i] == alphabet[j]){
                    encryptedVal=finalAlphabet[j];
                }
            }
            encryptedChar[i] = encryptedVal;
        }
        return new String(encryptedChar);
    }
}
