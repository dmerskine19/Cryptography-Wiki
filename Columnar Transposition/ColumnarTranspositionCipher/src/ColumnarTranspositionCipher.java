//*******************************************************************
//  @Drew Erskine
//
//  The program prompts the user to enter a message and a key, and then encrypts the message using the key by rearranging the characters in a grid and reading them off in a specific order.
//  The program then decrypts the encrypted message back to the original message using the same key.
//  The implementation uses a 2D array to store the message grid and an array to store the order of the columns based on the key.
//  The program assumes that the input message only contains letters, spaces, and punctuation, and that the key is a permutation of the integers from 1 to the length of the key.
//*******************************************************************
import java.util.Scanner;

public class ColumnarTranspositionCipher {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter the message to encrypt or decrypt
        System.out.print("Enter the message to encrypt or decrypt: ");
        String message = input.nextLine();

        // Prompt the user to enter the key for encryption or decryption
        System.out.print("Enter the key: ");
        String key = input.nextLine();

        // Initialize variables for the encrypted and decrypted messages
        String encryptedMessage = "";
        String decryptedMessage = "";

        // Get the length of the key
        int keyLength = key.length();

        // Calculate the number of rows needed for the message grid
        int messageLength = message.length();
        int numRows = messageLength / keyLength;
        if (messageLength % keyLength != 0) {
            numRows++;
        }

        // Initialize the message grid as a 2D array with the calculated dimensions
        char[][] messageGrid = new char[numRows][keyLength];

        // Fill the message grid with the characters from the message
        int messageIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (messageIndex < messageLength) {
                    messageGrid[i][j] = message.charAt(messageIndex);
                    messageIndex++;
                }
            }
        }

        // Initialize an array to store the order of the columns based on the key
        int[] columnOrder = new int[keyLength];
        for (int i = 0; i < keyLength; i++) {
            columnOrder[i] = key.indexOf(i + '1');
        }

        // Loop through the columns of the message grid in the order specified by the key
        for (int i = 0; i < keyLength; i++) {
            int column = columnOrder[i];

            // Add the characters in the column to the encrypted message
            for (int j = 0; j < numRows; j++) {
                encryptedMessage += messageGrid[j][column];
            }
        }

        // Loop through the rows of the message grid to create the decrypted message
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                int column = columnOrder[j];
                decryptedMessage += messageGrid[i][column];
            }
        }

        // Display the encrypted and decrypted messages to the user
        System.out.println("Encrypted message: " + encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}