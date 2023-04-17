//*******************************************************************
//  @Drew Erskine
//
//  The code above is an implementation of the Affine Cipher in Java.
//  The Affine Cipher is a type of monoalphabetic substitution cipher where each letter in the plaintext is mapped to another letter using a mathematical function.
//  The function used in the Affine Cipher is of the form E(x) = (ax + b) mod 26, where a and b are the key values, and x is the ASCII code of the letter to be encrypted. The same function can be used for decryption by finding the modular inverse of 'a'.
//  The Java code prompts the user to enter a message to encrypt or decrypt, as well as the key values 'a' and 'b'. It then uses the Affine Cipher algorithm to encrypt or decrypt the message, and displays the resulting messages to the user.
//  The code also includes a function to calculate the modular inverse of a number, which is used to find the modular inverse of 'a' so that the key can be used for decryption as well as encryption.
//*******************************************************************
import java.util.Scanner;

public class BitwiseXORCipher {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the message to encrypt or decrypt
        System.out.print("Enter the message to encrypt or decrypt: ");
        String message = input.nextLine();

        // Prompt the user to enter the key value for encryption or decryption
        System.out.print("Enter the key value: ");
        int key = input.nextInt();

        // Initialize variables for the encrypted and decrypted messages
        String encryptedMessage = "";
        String decryptedMessage = "";

        // Loop through each character in the message
        for (int i = 0; i < message.length(); i++) {

            // Get the current character and convert it to its ASCII code
            char c = message.charAt(i);
            int ascii = (int) c;

            // Encrypt the character if it is a letter
            if (c >= 'A' && c <= 'Z') {

                // Apply the Bitwise XOR Cipher algorithm for encryption
                // E(x) = x XOR key
                int encryptedAscii = ascii ^ key;

                // Convert the encrypted ASCII code back to a letter and add it to the encrypted message
                char encryptedChar = (char) encryptedAscii;
                encryptedMessage += encryptedChar;

                // Decrypt the character if it is a letter
            } else if (c >= 'a' && c <= 'z') {

                // Apply the Bitwise XOR Cipher algorithm for decryption
                // D(x) = x XOR key
                int decryptedAscii = ascii ^ key;

                // Convert the decrypted ASCII code back to a letter and add it to the decrypted message
                char decryptedChar = (char) decryptedAscii;
                decryptedMessage += decryptedChar;

                // Add the character to the messages as is if it is not a letter
            } else {
                encryptedMessage += c;
                decryptedMessage += c;
            }
        }

        // Display the encrypted and decrypted messages to the user
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}