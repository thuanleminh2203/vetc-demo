package com.mbbank.vetc.util;


import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Component
public class VETCSignAndVerify {

    @Value("${mini-app.privateKey}")
    private String MINI_APP_PRIVATE_KEY;

//    @Value("${mini-app.publicKey}")
//    private String MINI_APP_PUBLIC_KEY;

    @Value("${vetc.publicKey}")
    private String VETC_PUBLIC_KEY;


    public String sign(String content) {
        try {
            java.security.Signature signature = java.security.Signature.getInstance("SHA512withRSA");

            PrivateKey priKey = KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(MINI_APP_PRIVATE_KEY.getBytes("UTF-8"))));

            signature.initSign(priKey);
            signature.update(content.getBytes("UTF-8"));

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verify(String content, String signatureToBeVerified) {

        if (signatureToBeVerified == null || signatureToBeVerified.isEmpty()) {
            return false;
        }

        try {
            java.security.Signature signature = java.security.Signature.getInstance("SHA512withRSA");

            PublicKey pubKey = KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(VETC_PUBLIC_KEY.getBytes("UTF-8"))));

            signature.initVerify(pubKey);
            signature.update(content.getBytes("UTF-8"));

            return signature.verify(Base64.decodeBase64(signatureToBeVerified.getBytes("UTF-8")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

//    @PostConstruct
//    private void init() {
//        try {
//            String content = "VETC@123#BIDV|114100|16/05/2022 09:18:11|CRM_ERROR0038|Refid must be unique";
//            String contentVerify = "VETC@123#BIDV|114100|16/05/2022 09:18:11|CRM_ERROR0038|Refid must be unique";
//            String encode = sign(content);
////		String encode = "FLz9Sik5v8tzeqmgiPA/O1pOq8kAJ4goRnOu69pUgJKlzpAC6uptWYc/SN6DjSJ/FMQzt93IkJbVx84NaHdHa3WLl8I9zsQwllr4ZSU8EY3lIis59iO5sCxSDzUqjf/5xJVhFLSoKDyYcJ0/LZe/5VC/FkeHQ3f/6ZbrIl2C1oP7tRepYEHJ8UsQZlRHls8lRRS9H9dWF/yVzKrt+Wxw9uDFcJJt8oBugmWm/6krsaDe9DVmFLoy2qXQqnAIw5CmoZbso9FmHR6tMAVS61LF+hBsPvZNfswb7EwOfhAlLcQBMBJUyfhIRz5dF7Ij3DTREyfRpT4Jy7gLBjcQHOCQ8w==";
//            System.out.println("encode=======" + encode);
//            System.out.println("verify====" + verify(contentVerify, encode));
//        } catch (Exception e) {
//            System.out.println("==err===" + e);
//        }
//
//    }

}
