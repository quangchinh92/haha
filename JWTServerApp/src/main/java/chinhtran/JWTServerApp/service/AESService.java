package chinhtran.JWTServerApp.service;

public interface AESService {

    /**
     * Encrypt.
     *
     * @param plainText String
     * @return DecryptedText String
     */
    public String encrypt(String plainText);

    /**
     * Decrypt.
     *
     * @param encryptedText String
     * @return PlainText String
     */
    public String decrypt(String encryptedText);
}
