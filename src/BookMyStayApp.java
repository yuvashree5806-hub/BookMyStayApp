import java.util.*;

// 🔥 CUSTOM EXCEPTION (VERY IMPORTANT)
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

public class BookMyStayApp {

    static HashMap<Integer, String> bookings = new HashMap<>();
    static HashSet<Integer> bookedRooms = new HashSet<>();

    // 🔥 VALID ROOM TYPES (for validation)
    static HashSet<String> validRoomTypes = new HashSet<>(
            Arrays.asList("DELUXE", "STANDARD", "SUITE")
    );

    static int availableRooms = 2; // sample inventory

    public static void main(String[] args) {

        try {
            // ✅ Valid booking
            bookRoom(101, "Yuva", "DELUXE");

            // ❌ Invalid room type
            bookRoom(102, "Ram", "LUXURY");

        } catch (InvalidBookingException e) {
            System.out.println("❌ Booking Failed: " + e.getMessage());
        }

        System.out.println("\nSystem is still running safely ✅");
    }

    // 🔥 UC9 CORE METHOD
    public static void bookRoom(int roomNo, String name, String roomType)
            throws InvalidBookingException {

        // ✅ 1. VALIDATE ROOM TYPE
        if (!validRoomTypes.contains(roomType.toUpperCase())) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // ✅ 2. PREVENT DUPLICATE BOOKING
        if (bookedRooms.contains(roomNo)) {
            throw new InvalidBookingException("Room already booked: " + roomNo);
        }

        // ✅ 3. CHECK INVENTORY
        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available");
        }

        // ✅ 4. STATE UPDATE (ONLY AFTER VALIDATION)
        bookings.put(roomNo, name);
        bookedRooms.add(roomNo);
        availableRooms--;

        System.out.println("✅ Booking successful for " + name);
    }
}
