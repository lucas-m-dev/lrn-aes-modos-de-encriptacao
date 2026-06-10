import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "ESTAMOS NA AULA DE CRIPTOGRAFIA";
        String key = "12345678901234567890123456789012"; //32 caracters -> AES256
        byte[] ivBytes = new byte[16]; //cria um IV com 16 bytes de zero (pouco seguro, mas consistente - ótimo para o ex)

        System.out.println("\n===Modo CBC===");
        processMode("CBC", text, key, ivBytes);

        System.out.println("\n===Modo OFB===");
        processMode("OFB", text, key, ivBytes);

        System.out.println("\n===Modo CFB===");
        processMode("CFB", text, key, ivBytes);
    }


    public static void processMode(String mode, String text, String key, byte[] ivBytes) throws Exception{
        final String ALGORITHM = "AES";
        final String CHARSET = "UTF-8";
        final String HASH_ALGORITHM = "SHA-256";
        final String TRANSFORMATION = ALGORITHM + "/" + mode + "/PKCS5Padding";

        //Key
        MessageDigest sha256 = MessageDigest.getInstance(HASH_ALGORITHM);   //cria objeto que implementa o sha 256
        byte[] byteKey = key.getBytes(CHARSET);                             //Converte a string a um Array de bytes
        byte[] hashedKey = sha256.digest(byteKey);                          //Aplica o algoritmo sha 256 sobre a Key e gera um Array de 32 bytes (128 bits)
        SecretKeySpec secretKey = new SecretKeySpec(hashedKey,ALGORITHM);   //cria Key de AES real

        //Cipher and IV objects
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);      //cria objeto que representa IV
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);         //cria objeto Cipher configurado com modo e padding

        //Encrypt
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);        //inicializa objeto Cipher com modo de operação, chave e IV
        byte[] textBytes = text.getBytes(CHARSET);                  //converte text para Array de bytes
        byte[] encryptedBytes = cipher.doFinal(textBytes);          //Encripta os bytes com o algoritmo AES e o modo escolhido
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes); //converte para Base64 -> texto legivel
        System.out.println("Criptograma \t\t" + encryptedText);

        //Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);        //inicializa objeto Cipher com modo de operação, chave e IV
        encryptedBytes = Base64.getDecoder().decode(encryptedText); //converte de Base64 para um Array de bytes
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);     //Decifra os bytes com AES
        String decryptedText = new String(decryptedBytes, CHARSET); //Converte de bytes para texto normal
        System.out.println("Texto Decifrado \t" + decryptedText);
    }
}
