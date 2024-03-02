import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hotel {
    private List<Room> rooms;

    public Hotel(){
        rooms = new LinkedList<>();
        generateRooms();
    }

    private void generateRooms(){
        Random random = new Random();
        for (int i = 1;i <= 400;i = i+100){
            for (int j = 0;j <= 30;j++){
                if(i == 1) {
                    j = 1;
                    i = 0;
                }
                rooms.add(new Room(i+j,random.nextInt(1,4),0,false,LocalDate.of(2022,02,11),LocalDate.of(2032,03,12),null));
            }
        }
        findRoom(211).setDateTo(LocalDate.of(2021,03,11));
    }

    public void availability(LocalDate date){
        if (date == null){
            date = LocalDate.now();
        }
        Room r;
        for (int i = 0;i < rooms.size();i++){
            r = rooms.get(i);
            if (r.isAvailable() ||
                    (r.getDateFrom().compareTo(date) > 0 &&
                            r.getDateTo().compareTo(date) < 0)){
                System.out.println("Room "+r.getNumber());
            }
        }
    }

    public void checkIn(int number, String dateFrom,String dateTo,String note,Integer guests){
        Room room = findRoom(number);
        if (room.isAvailable()){
            room.setAvailable(false);
            room.setDateFrom(LocalDate.parse(dateFrom));
            room.setDateTo(LocalDate.parse(dateTo));
            room.setNote(note);
            if (guests == null){
                room.setNumberOfGuests(room.getNumberOfBeds());
            }
            else room.setNumberOfGuests(guests);
        }
        else{
            System.out.println("This rooms is currently unavailable!");
        }
    }

    public void checkOut(int number){
        findRoom(number).setAvailable(true);
    }

    public Room findRoom(int number){

        for(int i = 0;i<rooms.size();i++){
            if (rooms.get(i).getNumber() == number){
                return rooms.get(i);
            }
        }
        return null;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
