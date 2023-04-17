//*******************************************************************
// @Drew Erskine
// The Java code above implements the One-Time Pad cipher, which is a type of symmetric key encryption that uses a random key that is at least as long as the message being encrypted.
// The code defines three functions:
// generateKey to generate a random key of the specified length,
// encrypt to encrypt a message using the one-time pad cipher,
// and decrypt to decrypt a message using the same cipher.
//*******************************************************************
import java.util.Random;

public class OneTimePadCipher {

    // Function to generate a random key of the specified length
    private static byte[] generateKey(int keyLength) {
        byte[] key = new byte[keyLength];
        new Random().nextBytes(key);
        return key;
    }

    // Function to encrypt a message using the one-time pad cipher
    private static byte[] encrypt(byte[] message, byte[] key) {
        byte[] ciphertext = new byte[message.length];
        for (int i = 0; i < message.length; i++) {
            ciphertext[i] = (byte) (message[i] ^ key[i]); // XOR the message byte with the corresponding key byte
        }
        return ciphertext;
    }

    // Function to decrypt a message using the one-time pad cipher
    private static byte[] decrypt(byte[] ciphertext, byte[] key) {
        byte[] message = new byte[ciphertext.length];
        for (int i = 0; i < ciphertext.length; i++) {
            message[i] = (byte) (ciphertext[i] ^ key[i]); // XOR the ciphertext byte with the corresponding key byte
        }
        return message;
    }

    public static void main(String[] args) {

        // Generate a random key of length 16 bytes (128 bits)
        byte[] key = generateKey(16);

        // Define the message to be encrypted
        String message = "Hello, world!";

        // Convert the message to a byte array
        byte[] plaintext = message.getBytes();

        // Encrypt the message using the one-time pad cipher
        byte[] ciphertext = encrypt(plaintext, key);

        // Decrypt the message using the one-time pad cipher
        byte[] decrypted = decrypt(ciphertext, key);

        System.out.println(new String(decrypted));
    }
}
