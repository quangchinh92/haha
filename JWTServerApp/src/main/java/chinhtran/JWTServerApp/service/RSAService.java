package chinhtran.JWTServerApp.service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface RSAService {
  // Tạo cặp khóa (Public Key và Private Key)
  public KeyPair createKeyPair();

  // Mã hóa dữ liệu bằng Public Key
  public String encrypt(String plainText, PublicKey publicKey);

  // Giải mã dữ liệu bằng Private Key
  public String decrypt(String encryptedText, PrivateKey privateKey);
}
