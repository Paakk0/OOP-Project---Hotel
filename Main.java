import Commands.CommandFactory;
import Model.Hotel;

import java.util.Scanner;

/**
 * The main class that initializes the hotel management system and handles user interaction.
 */
public class Main {
    /**
     * The main entry point of the hotel management system.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the hotel and generate rooms
        Hotel hotel = new Hotel();
        Hotel.generateRooms();

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Enter an infinite loop to continuously accept user input
        while (true) {
            // Execute the user-entered command
            CommandFactory.executeCommand(scanner.nextLine());
        }
    }
}
