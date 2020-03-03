package com.example.crypto.domain;

public class Encryptor {

    EncryptionStrategy strategy;

    public Encryptor() {
    }

    public Encryptor(EncryptionStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public String encrypt(String plainText){
        return strategy.encrypt(plainText);
    }

    public void setKeyword(char[] keyword){
        if(keyword.length!=0)
        strategy.setKeyword(keyword);
    }
}
