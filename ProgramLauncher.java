package UI;

import Commands.CommandFactory;
import Model.Hotel;

import java.util.Scanner;

public class ProgramLauncher {
    public static void Launch() {
        Hotel hotel = new Hotel();
        Hotel.generateRooms();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(ColorCode.NORMAL.getCode() + "> ");
            CommandFactory.executeCommand(scanner.nextLine());
        }
    }
}
