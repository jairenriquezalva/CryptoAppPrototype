package com.example.crypto.domain;

public interface EncryptionStrategy {
    String encrypt(String plainText);
    void setKeyword(char[] keyword);
}
