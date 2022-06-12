package com.mbbank.vetc.util;

import com.mbbank.vetc.constant.AppConstants;
import com.mbbank.vetc.payload.request.TransactionCallbackRequest;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class FunctionUtils {

    @Value("${vetc.merchantCode}")
    private static String MERCHANT_CODE;

    @Value("${vetc.checksumKey}")
    private static String CHECKSUM_KEY;

    @Value("${vetc.publicKey}")
    private static String VETC_PUBLIC_KEY;

    @SneakyThrows
    public static String hmac256(String data) {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(CHECKSUM_KEY.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
        System.out.println(hash);

        return hash;
    }

    @SneakyThrows
    public static boolean checkChecksum(TransactionCallbackRequest transaction) {
        var currentCheckSum = transaction.getChecksum();
        String data = new StringBuilder(MERCHANT_CODE)
                .append(transaction.getId())
                .append(AppConstants.VETC_TRANSACTION_TYPE)
                .append(transaction.getCif())
                .append(transaction.getAmount())
                .append(transaction.getStatus())
                .toString();
        return currentCheckSum.equals(hmac256(data));
    }



    // Create base64 encoded signature using SHA256/RSA.
    @SneakyThrows
    private static String signSHA256RSA(String input)  {
        // Remove markers and new line characters in private key
        String realPK = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjMt9vifAV1mGcIAm/3oZ5oSK5VKreSz/xsMFWXgZkydYuFLwD7822Dz98H4ms/AjjHwiRxxQh3Foy4VFitz4RCNJsH7Gc8/vwPMViTHfYqrua54S9Q71J0joW6I1RqHJeiK314KN1BZkvC6+SoOgiwVsdF6C5OGiti19LNLgL5KHFGwgIhLFuVN1tVzoq4uIjxxQdayo8OmZLzzMwYuKZ52fVbCcSWFZFFsgH4+tu95gazesgJlb+CYqd0ojbrOzCU6sPGiudWQPPGELF1Z7rwkCW4pIWxuy8CEK5I+ER1oH+C76spKofIYZEoOPfla7iW+kglhQAAh+2LokDXKBzAgMBAAECggEBAJGBijWohqbxvZW1+vtXLA7F3G/hTXdlIF4dekPHEWxLiOsNEZVw+biZX8ELXXUeQc4+nSVcmlaWYNV8j1O0jWIiu3orx/BomPhZNqOuCizkDODQC0m0W9C135UNQvLLfS1ML85Ju3NaynlnseLKFC7otpP5vRNnd+1DlnM3TeFLSstZ9GKkaHWOVrpjTaKhitYp2F9kvJeIoF3GcJUQ6vYxBWGAKiNUw3iFKQWNm9uLbvLeJkcGcoiWkIy/Y4n6gg/7xp4lK4VI+OalzatBMRG1d02vvRiePSytQXrxj2casrHv+eJzXo3NV18ubK06RJJKSznHd5PztoMxs74NiAECgYEA2RZ8IypsU+SXC2+msydOktyngs8HmpZevKVZa+qtVtCp+8VJ/hB+rVJxfQVTwvtrxG/VIJ6CNS1tuVZP0e31QRfB+MEgiBqR2Qim536Jk2LGi8opx81a9SqPvctVVykBJaHPIe9QnLE0ijg8hEhCBbucRrFWpLGhbZtbSeVeMhECgYEAwHOTrezY8pgU+BYcKeWmndMW868jOmyif2w/mXKQ0HOY2Z21UPK2uJd6znfPGbDHyQUzkE1Ktus0zXrAwCP7eSY7hDTUBgYNjdN8n563CBZp3JoUbo+uUzWYj/f1MBNE2D090n6aJYnF2M3WuOpu69SHJVxhJXE8wmhRdrTXJkMCgYBT0/3tqmUYZC37aYrqpWQPDAvXVFCwXh6JJCSKqp1y57KsGTwAUqhID4zpZRQ6N9IekbywEuVe2YMETZMXEpdhImZxw0nnpLLrJw8kV1WSx1K6Cj/l+qOEBoJpHMqAlMHvoTMV/I6ZY0hRk3Oy9SAhjJfFKJe3kywt4g73Uw3zsQKBgHId1osCb55iWcTF74tUdtkNC4YR6vTA6BZgzNGjooVIlnEr2BEY6wpIIHT/jBlyvvTPFcVSX6RZkiTpzhPhAkPTTOVXIgY0XqMjrblEtsmf6MP7xXPN1RPYKw/re0M3mdITdm4zzhnGOUAWxmU99en3Su/3eE9odxvcGbom/5DbAoGATA5MKLlEH8RMCSzHoFM1RZLGagDJL31iQCdAHGR2XD0Pq5L35UckctZ35bZb1Ta4gf0zZb6VfynBsUFmzFo2jMx/RWTSOrMT1ZMACPDVQ2LYbuzAf2MBLFQY83Q8T4DFTQcbUY2dttPqX2jARZD7S60dQCrKkzP5ZvEknhGsngY=";
        byte[] b1 = java.util.Base64.getDecoder().decode(realPK);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        Signature privateSignature = Signature.getInstance("SHA512withRSA");
        privateSignature.initSign(kf.generatePrivate(spec));
        privateSignature.update(input.getBytes("UTF-8"));
        byte[] s = privateSignature.sign();
        return java.util.Base64.getEncoder().encodeToString(s);
    }


    public static void main(String[] args) throws GeneralSecurityException {
        var aa = signSHA256RSA("test");
//        String message = "this is the important message to sign";
        // this is a SAMPLE and UNSECURE RSA 512 bit key
        String signedData = "HS4qvrXpqu97me7yDt9lWXp+QLjKMO8FY4kiUiGhMhi6KmXQXCtmcUWSbg0i+LXv7u5ueRiQNeBnu6UCbPhZLg==";
        String rsaInstanceString = "SHA1withRSA";

        PublicKey publicKey = getPublicKeyFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAozLfb4nwFdZhnCAJv96GeaEiuVSq3ks/8bDBVl4GZMnWLhS8A+/Ntg8/fB+JrPwI4x8IkccUIdxaMuFRYrc+EQjSbB+xnPP78DzFYkx32Kq7mueEvUO9SdI6FuiNUahyXoit9eCjdQWZLwuvkqDoIsFbHReguThorYtfSzS4C+ShxRsICISxblTdbVc6KuLiI8cUHWsqPDpmS88zMGLimedn1WwnElhWRRbIB+PrbveYGs3rICZW/gmKndKI26zswlOrDxornVkDzxhCxdWe68JAluKSFsbsvAhCuSPhEdaB/gu+rKSqHyGGRKDj35Wu4lvpIJYUAAIfti6JA1ygcwIDAQAB");

        boolean verifyData = verifyRsa(publicKey, "SHA1withRSA", "test".getBytes(StandardCharsets.UTF_8), java.util.Base64.getDecoder().decode(aa));


        if (verifyData = true) {
            System.out.println("The data was verified.");
        } else {
            System.out.println("The data could NOT get verified.");
        }

    }

    public static PublicKey getPublicKeyFromString(String key) throws GeneralSecurityException {
        String publicKeyPEM = key;
        byte[] encoded = java.util.Base64.getDecoder().decode(publicKeyPEM);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pubKey = (PublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        return pubKey;
    }

    public static Boolean verifyRsa(PublicKey publicKey, String rsaInstanceString, byte[] messageByte,
                                    byte[] signatureByte) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature publicSignature = Signature.getInstance(rsaInstanceString);
        publicSignature.initVerify(publicKey);
        publicSignature.update(messageByte);
        return publicSignature.verify(signatureByte);
    }

}
