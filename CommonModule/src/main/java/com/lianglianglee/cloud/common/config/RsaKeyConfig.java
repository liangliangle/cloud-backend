package com.lianglianglee.cloud.common.config;

// import org.apache.commons.codec.binary.Base64;

import org.jose4j.base64url.Base64;
import org.jose4j.jwk.RsaJsonWebKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class RsaKeyConfig {


  @Bean
  public RsaJsonWebKey getRsaJsonWebKey() {
    RsaJsonWebKey rsaJsonWebKey = null;
    try {
      RSAPublicKey publicKey = getPublicKey(readByteArrayFromBase64String(readStringFromFile("/publicKey.keystore")));
      RSAPrivateKey privateKey = getPrivateKey(readByteArrayFromBase64String(readStringFromFile("/privateKey.keystore")));
      rsaJsonWebKey = new RsaJsonWebKey(publicKey);
      rsaJsonWebKey.setPrivateKey(privateKey);
      rsaJsonWebKey.setKeyId("bid-sec");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return rsaJsonWebKey;
  }

  private byte[] readByteArrayFromBase64String(String input) {
    return new Base64().decode(input);
  }

  private RSAPublicKey getPublicKey(byte[] keyBytes)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return (RSAPublicKey) keyFactory.generatePublic(keySpec);
  }

  private RSAPrivateKey getPrivateKey(byte[] keyBytes)
    throws NoSuchAlgorithmException, InvalidKeySpecException {
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return (RSAPrivateKey) privateKey;
  }

  private String readStringFromFile(String fileName) {
    StringBuffer buffer = new StringBuffer();
    try {
      String encoding = "utf-8";
      InputStream inputStream = this.getClass().getResourceAsStream(fileName);
      // logger.info(file.getPath());
      InputStreamReader read = new InputStreamReader(inputStream, encoding);
      BufferedReader bufferedReader = new BufferedReader(read);
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        buffer.append(line);
      }
      read.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }
}