import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BitwiseXORCipher {

    public static void main(String[] args) throws Exception {

        // Create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the message to encrypt or decrypt
        System.out.println("Message to encrypt or decrypt: Hello, world!");
        String message = "Hello, world!";

        // Prompt the user to enter the key value for encryption or decryption
        System.out.println("Key value: mysecretpassword");
        String keyString = "mysecretpassword";

        // Convert the key string to a byte array
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);

        // Create a SecretKeySpec object from the key byte array
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Create a Cipher object and initialize it for encryption with the secret key
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Encrypt the message
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

        // Create a new Cipher object and initialize it for decryption with the secret key
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // Decrypt the message
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);


        // Display the encrypted and decrypted messages to the user
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}