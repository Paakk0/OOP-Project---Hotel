import Commands.CommandFactory;
import Model.Hotel;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.generateRooms();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            CommandFactory.executeCommand(scanner.nextLine());
        }
    }

    static void fillRooms(Hotel hotel, int number) {
        for (int i = 0; i < hotel.getRooms().size(); i++) {
            if (hotel.getRooms().get(i).getNumber() != number) {
                hotel.checkIn(hotel.getRooms().get(i).getNumber(), LocalDate.of(2000, 1, 1), LocalDate.of(2000, 12, 30), "Somenote", null);
            }
        }
    }
}

//checkin 429 2012-04-22 2012-04-24 business 3
//find 3 2012-04-22 2012-04-24
//find! 2 2012-04-22 2012-04-24