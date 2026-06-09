package chinhtran.JWTServerApp.service;

import chinhtran.JWTServerApp.dto.AppAESKeyDto;

public interface AppAESService {

  /**
   * Encrypt.
   *
   * @param key AppAESKeyDto
   * @param plainText String
   * @return DecryptedText String
   */
  public String encrypt(AppAESKeyDto key, String plainText);

  /**
   * Decrypt.
   *
   * @param key AppAESKeyDto
   * @param encryptedText String
   * @return PlainText String
   */
  public String decrypt(AppAESKeyDto key, String encryptedText);
}
