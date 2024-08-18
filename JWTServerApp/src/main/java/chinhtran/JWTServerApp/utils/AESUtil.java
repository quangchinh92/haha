package chinhtran.JWTServerApp.utils;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

  private static String AES_ALGORITHM = "AES";

  private static String AES_CBC_ALGORITHM = "AES/CBC/PKCS5Padding";

  private static String PBKDF2_SHA256 = "PBKDF2WithHmacSHA256";

  private static int ITERATION_COUNT = 65536;

  private static int KEY_LENGTH = 256;

  /**
   * Encrypt.
   *
   * @param input String
   * @param key SecretKey
   * @param iv IvParameterSpec
   * @return EncryptedText
   */
  public static String encrypt(String input, SecretKey key, IvParameterSpec iv) {

    try {
      Cipher cipher = Cipher.getInstance(AES_CBC_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key, iv);
      byte[] cipherText;
      cipherText = cipher.doFinal(input.getBytes());
      return Base64.getEncoder().encodeToString(cipherText);
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | InvalidAlgorithmParameterException
        | IllegalBlockSizeException
        | BadPaddingException e) {
      throw new RuntimeException("Encrypt function of AESUtil failed!", e);
    }
  }

  /**
   * Decrypt.
   *
   * @param cipherText String
   * @param key SecretKey
   * @param iv IvParameterSpec
   * @return PlainText
   */
  public static String decrypt(String cipherText, SecretKey key, IvParameterSpec iv) {
    try {
      Cipher cipher = Cipher.getInstance(AES_CBC_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key, iv);
      byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
      return new String(plainText);
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | InvalidAlgorithmParameterException
        | IllegalBlockSizeException
        | BadPaddingException e) {
      throw new RuntimeException("decrypt function of AESUtil failed!", e);
    }
  }

  public static SecretKey getKeyFromPassword(String password, String salt) {
    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_SHA256);
      KeySpec spec =
          new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
      SecretKey secret =
          new SecretKeySpec(factory.generateSecret(spec).getEncoded(), AES_ALGORITHM);
      return secret;

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("getKeyFromPassword function of AESUtil failed!", e);
    }
  }

  public static IvParameterSpec getIvFromString(String ivString) {
    byte[] iv = ivString.getBytes(Charset.forName("UTF-8"));
    return new IvParameterSpec(iv);
  }
}
