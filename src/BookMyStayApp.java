import java.util.*;

class InvalidCancellationException extends Exception {
    public InvalidCancellationException(String message) {
        super(message);
    }
}

public class BookMyStayApp {

    static HashMap<Integer, String> bookings = new HashMap<>();
    static HashMap<Integer, String> roomTypes = new HashMap<>();
    static HashSet<Integer> bookedRooms = new HashSet<>();
    static HashMap<String, Integer> inventory = new HashMap<>();
    static Stack<Integer> releasedRooms = new Stack<>();

    public static void main(String[] args) {

        inventory.put("DELUXE", 2);
        inventory.put("STANDARD", 1);

        try {
            createBooking(101, "Yuva", "DELUXE");
            cancelBooking(101);
            cancelBooking(101);
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }

        displayState();
    }

    public static void createBooking(int roomNo, String name, String type) {

        bookings.put(roomNo, name);
        roomTypes.put(roomNo, type);
        bookedRooms.add(roomNo);
        inventory.put(type, inventory.get(type) - 1);

        System.out.println("✅ Booking created for Room " + roomNo);
    }

    public static void cancelBooking(int roomNo) throws InvalidCancellationException {

        if (!bookedRooms.contains(roomNo)) {
            throw new InvalidCancellationException("No active booking found for Room " + roomNo);
        }

        String type = roomTypes.get(roomNo);

        releasedRooms.push(roomNo);

        bookings.remove(roomNo);
        roomTypes.remove(roomNo);
        bookedRooms.remove(roomNo);

        inventory.put(type, inventory.get(type) + 1);

        System.out.println("↩️ Booking cancelled for Room " + roomNo);
    }

    public static void displayState() {
        System.out.println("Bookings: " + bookings);
        System.out.println("Inventory: " + inventory);
        System.out.println("Rollback Stack: " + releasedRooms);
    }
}