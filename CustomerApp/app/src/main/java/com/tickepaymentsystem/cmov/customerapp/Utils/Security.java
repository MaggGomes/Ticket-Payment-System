package com.tickepaymentsystem.cmov.customerapp.Utils;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;

import javax.security.auth.x500.X500Principal;

public class Security {

    public static PublicKey generateRSAKeypair(Context context, String alias) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException, InvalidAlgorithmParameterException, NoSuchProviderException {

        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        PrivateKey privateKey = (PrivateKey) keyStore.getKey("alias", null);

        if (privateKey != null)
            return keyStore.getCertificate("alias").getPublicKey();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.YEAR, 1);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
        keyPairGenerator.initialize(
                new KeyPairGeneratorSpec.Builder(context)
                        .setKeySize(512)
                        .setAlias(alias)
                        .setSubject(new X500Principal("CN=cmov"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build());

        // generate key pair and return the public key
        return keyPairGenerator.generateKeyPair().getPublic();
    }

    /*public static String signMessage(String alias, JSONObject msg) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException, InvalidKeyException, SignatureException {

        // get private key
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(alias, null);
        if (entry == null) {
            return "";
        }
        PrivateKey privateKey = ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();

        // sign message
        byte[] bMsg = msg.toString().getBytes("UTF-8");
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(bMsg);
        byte[] bSignature = signature.sign();

        // encode signature to base64 string and return it
        return Base64.encodeToString(bSignature , Base64.DEFAULT | Base64.NO_WRAP);
    }*/
}
