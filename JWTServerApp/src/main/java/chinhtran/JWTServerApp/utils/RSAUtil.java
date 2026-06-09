package chinhtran.JWTServerApp.utils;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtil {

  // Tạo cặp khóa (Public Key và Private Key)
  public static KeyPair generateKeyPair() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048); // Độ dài khóa 2048 bits là tiêu chuẩn bảo mật hiện tại
    return generator.generateKeyPair();
  }

  // Mã hóa dữ liệu bằng Public Key
  public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] bytes = plainText.getBytes("UTF-8");
    byte[] encryptedBytes = encryptCipher.doFinal(bytes);
    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  // Giải mã dữ liệu bằng Private Key
  public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
    byte[] bytes = Base64.getDecoder().decode(encryptedText);
    Cipher decryptCipher = Cipher.getInstance("RSA");
    decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedBytes = decryptCipher.doFinal(bytes);
    return new String(decryptedBytes, "UTF-8");
  }

  public static PublicKey getRsaPublicKey(String publicKeyStr) throws Exception {
    // 1. Clean the string if it contains PEM headers/footers or newlines
    String cleanedKey =
        publicKeyStr
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\\s+", ""); // Removes all newlines and spaces

    // 2. Base64 decode the string into bytes
    byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

    // 3. Create the X509 key specification
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);

    // 4. Generate the public key using the RSA KeyFactory
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(spec);
  }

  public static PrivateKey getPrivateKeyFromString(String keyString) throws Exception {
    // 1. Decode the Base64 string into bytes
    byte[] keyBytes = Base64.getDecoder().decode(keyString.trim());

    // 2. Create the PKCS8 key specification
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

    // 3. Generate the PrivateKey instance via KeyFactory
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(spec);
  }
}
