/**
 * BookMyStay Application
 * Demonstrates room modeling using abstraction and inheritance.
 *
 * @author Yuvashree
 * @version 1.0
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : " + price);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        System.out.println("Available Rooms\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailability + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailability + "\n");

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailability + "\n");

    }
}