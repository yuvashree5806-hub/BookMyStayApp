import java.util.*;

/**
 * Reservation class represents a guest booking request.
 */
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

/**
 * Inventory service maintains room availability.
 */
class InventoryService {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

/**
 * Booking service handles allocation and confirmation.
 */
class BookingService {

    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();

    public void processBookings(Queue<Reservation> queue, InventoryService inventory) {

        while (!queue.isEmpty()) {

            Reservation request = queue.poll();
            String roomType = request.roomType;

            if (inventory.getAvailability(roomType) > 0) {

                // Generate unique room ID
                String roomId = roomType + "-" + UUID.randomUUID().toString().substring(0,5);

                // Ensure uniqueness
                while (allocatedRoomIds.contains(roomId)) {
                    roomId = roomType + "-" + UUID.randomUUID().toString().substring(0,5);
                }

                allocatedRoomIds.add(roomId);

                roomAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                // Update inventory
                inventory.decreaseAvailability(roomType);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + request.guestName);
                System.out.println("Room Type: " + roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();

            } else {
                System.out.println("No rooms available for " + request.guestName);
            }
        }
    }
}

/**
 * Main Application
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Single"));

        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService();

        bookingService.processBookings(bookingQueue, inventory);
    }
}