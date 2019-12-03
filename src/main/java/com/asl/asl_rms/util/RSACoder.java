package com.asl.asl_rms.util;

import java.io.PrintStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public abstract class RSACoder extends Coder
{
  public static final String KEY_ALGORITHM = "RSA";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";

  public static String sign(byte[] data, String privateKey)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(privateKey);

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initSign(priKey);
    signature.update(data);

    return encryptBASE64(signature.sign());
  }

  public static boolean verify(byte[] data, String publicKey, String sign)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(publicKey);

    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

    KeyFactory keyFactory = KeyFactory.getInstance("RSA");

    PublicKey pubKey = keyFactory.generatePublic(keySpec);

    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initVerify(pubKey);
    signature.update(data);

    return signature.verify(decryptBASE64(sign));
  }

  public static byte[] decryptByPrivateKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(2, privateKey);

    return cipher.doFinal(data);
  }

  public static byte[] decryptByPublicKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key publicKey = keyFactory.generatePublic(x509KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(2, publicKey);

    return cipher.doFinal(data);
  }

  public static byte[] encryptByPublicKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);

    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key publicKey = keyFactory.generatePublic(x509KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(1, publicKey);

    return cipher.doFinal(data);
  }

  public static byte[] encryptByPrivateKey(byte[] data, String key)
    throws Exception
  {
    byte[] keyBytes = decryptBASE64(key);

    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(1, privateKey);

    return cipher.doFinal(data);
  }

  public static String getPrivateKey(Map<String, Object> keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPrivateKey");

    return encryptBASE64(key.getEncoded());
  }

  public static String getPublicKey(Map<String, Object> keyMap)
    throws Exception
  {
    Key key = (Key)keyMap.get("RSAPublicKey");

    return encryptBASE64(key.getEncoded());
  }

  public static Map<String, Object> initKey()
    throws Exception
  {
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

    keyPairGen.initialize(1024);

    KeyPair keyPair = keyPairGen.generateKeyPair();

    RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();

    RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();

    Map keyMap = new HashMap(2);

    keyMap.put("RSAPublicKey", publicKey);
    keyMap.put("RSAPrivateKey", privateKey);
    return keyMap;
  }

  public static void main(String[] args)
    throws Exception
  {
    Map keyMap = initKey();

    String publicKey = getPublicKey(keyMap);
    String privateKey = getPrivateKey(keyMap);
    System.err.println("公钥: \n\r" + publicKey);
    System.err.println("私钥： \n\r" + privateKey);

    System.err.println("私钥加密——公钥解密");
    String inputStr = "1232012-12-120002-06A7-BFEB-FBFF-1DBA-E3BFAT/AT COMPATIBLE ACRSYS - 1160J3320082GL52LA";
    byte[] data = inputStr.getBytes();

    System.err.println("私钥签名——公钥验证签名");

    String sign = sign(data, privateKey);
    System.err.println("签名:\r" + sign);

    boolean status = verify(data, publicKey, sign);
    System.err.println("状态:\r" + status);
  }
}