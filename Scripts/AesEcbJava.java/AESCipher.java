import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import java.util.Base64;

public class AESCipher {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String CHARSET = "UTF-8";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int AES_KEY_LENGTH = 16; // 128 bits

    public static SecretKeySpec keyNormalizer(String key_orig) throws Exception{
        // Hash SHA-256
        MessageDigest sha256 = MessageDigest.getInstance(HASH_ALGORITHM);

        //Key to Bytes
        byte[] byteKey = key_orig.getBytes(CHARSET);
        //Apply SHA-256
        byte[] hashedKey = sha256.digest(byteKey);
        // Key to 16 Bytes (128 bits)
        byte[] aesKey = Arrays.copyOf(hashedKey, AES_KEY_LENGTH);

        // Creates AES key from bytes
        SecretKeySpec secretKey = new SecretKeySpec(aesKey,ALGORITHM);
        return secretKey;
    }

    public static String encrypt(String data, String key) throws Exception{
        //SecretKey
        SecretKeySpec secretKey = keyNormalizer(key);

        //ECB Cipher
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //Data to Bytes then Encrypt
        byte[] dataBytes = data.getBytes(CHARSET);
        byte[] encryptedBytes = cipher.doFinal(dataBytes);

        //Codify in Base64 -> Legible text
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }

    public static String decrypt(String encryptedData, String key) throws Exception{
        //SecretKey
        SecretKeySpec secretKey = keyNormalizer(key);

        //ECB Decipher
        Cipher decipher = Cipher.getInstance(TRANSFORMATION);
        decipher.init(Cipher.DECRYPT_MODE, secretKey);

        //From Base64 to Data in bytes
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        //Decrypt data then to normal text
        byte[] decryptedBytes = decipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes, CHARSET);
        return decryptedText;
    }
}
