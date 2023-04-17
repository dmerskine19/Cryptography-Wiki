//*******************************************************************
//  @Drew Erskine
//
//  The Java code above is an implementation of the Vigenere cipher, a classic encryption algorithm that uses a key string to encrypt and decrypt a message.
//  To encrypt or decrypt the message, the code generates a repeating key that is the same length as the plaintext or ciphertext message.
//  It then iterates over each character of the message and corresponding character of the key, converting each character to a numeric value using an encoding table, and adding or subtracting the corresponding values modulo 26 to obtain the ciphertext or plaintext character.
//*******************************************************************
public class VigenereCipher {
    private static final int ALPHABET_SIZE = 26;

    // Returns a repeating key that is the same length as the plaintext message
    private static String generateRepeatingKey(String key, int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(key);
        }
        return sb.substring(0, length);
    }

    // Encrypts a plaintext message using a Vigenere cipher and a key string
    public static String encrypt(String plaintext, String key) {
        String repeatingKey = generateRepeatingKey(key, plaintext.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            int plainValue = c - 'A';
            int keyValue = repeatingKey.charAt(i) - 'A';
            int cipherValue = (plainValue + keyValue) % ALPHABET_SIZE;
            char cipherChar = (char) (cipherValue + 'A');
            sb.append(cipherChar);
        }
        return sb.toString();
    }

    // Decrypts a ciphertext message using a Vigenere cipher and a key string
    public static String decrypt(String ciphertext, String key) {
        String repeatingKey = generateRepeatingKey(key, ciphertext.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            int cipherValue = c - 'A';
            int keyValue = repeatingKey.charAt(i) - 'A';
            int plainValue = (cipherValue - keyValue + ALPHABET_SIZE) % ALPHABET_SIZE;
            char plainChar = (char) (plainValue + 'A');
            sb.append(plainChar);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String plaintext = "YOUR_MESSAGE_HERE";
        String key = "SECRET";

        // Encrypt the plaintext message
        String ciphertext = VigenereCipher.encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext message
        String decryptedText = VigenereCipher.decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}