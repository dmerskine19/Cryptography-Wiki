//*******************************************************************
//  @Drew Erskine
//
//  The program prompts the user to enter a message and a key, and then encrypts the message using the key by rearranging the characters in a grid and reading them off in a specific order.
//  The program then decrypts the encrypted message back to the original message using the same key.
//  The implementation uses a 2D array to store the message grid and an array to store the order of the columns based on the key.
//  The program assumes that the input message only contains letters, spaces, and punctuation, and that the key is a permutation of the integers from 1 to the length of the key.
//*******************************************************************
import java.util.Scanner;

public class AffineCipher {

    // Define a function to calculate the modular inverse of a number
    // This is used to find the modular inverse of the key value 'a'
    // so that the key can be used for decryption as well as encryption
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        // Create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the message to encrypt or decrypt
        System.out.print("Enter the message to encrypt or decrypt: ");
        String message = input.nextLine();

        // Prompt the user to enter the key values for encryption or decryption
        System.out.print("Enter the key values 'a' and 'b' (separated by a space): ");
        int a = input.nextInt();
        int b = input.nextInt();

        // Calculate the modular inverse of 'a' so that the key can be used for decryption as well
        int aInverse = modInverse(a, 26);
        if (aInverse == -1) {
            System.out.println("Invalid key. 'a' must be coprime with 26.");
            return;
        }

        // Initialize variables for the encrypted and decrypted messages
        StringBuilder encryptedMessage = new StringBuilder();
        StringBuilder decryptedMessage = new StringBuilder();

        // Loop through each character in the message
        for (int i = 0; i < message.length(); i++) {

            // Get the current character and convert it to its ASCII code
            char c = message.charAt(i);

            // Encrypt the character if it is a letter
            if (c >= 'A' && c <= 'Z') {

                // Apply the Affine Cipher algorithm for encryption
                // E(x) = (ax + b) mod 26
                int encryptedAscii = ((a * ((int) c - 65)) + b) % 26;

                // Convert the encrypted ASCII code back to a letter and add it to the encrypted message
                char encryptedChar = (char) (encryptedAscii + 65);
                encryptedMessage.append(encryptedChar);

                // Decrypt the character if it is a letter
            } else if (c >= 'a' && c <= 'z') {

                // Apply the Affine Cipher algorithm for decryption
                // D(x) = a^-1 * (x - b) mod 26
                int decryptedAscii = (aInverse * (((int) c - 97) - b + 26)) % 26;

                // Convert the decrypted ASCII code back to a letter and add it to the decrypted message
                char decryptedChar = (char) (decryptedAscii + 97);
                decryptedMessage.append(decryptedChar);

                // Add the character to the messages as is if it is not a letter
            } else {
                encryptedMessage.append(c);
                decryptedMessage.append(c);
            }
        }

        // Display the encrypted and decrypted messages to the user
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}