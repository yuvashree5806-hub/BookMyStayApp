import java.util.*;

class BookingRequest {
    int roomNo;
    String guestName;
    String roomType;

    BookingRequest(int roomNo, String guestName, String roomType) {
        this.roomNo = roomNo;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class BookMyStayApp {

    static Queue<BookingRequest> bookingQueue = new LinkedList<>();
    static HashMap<String, Integer> inventory = new HashMap<>();
    static HashSet<Integer> bookedRooms = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        inventory.put("DELUXE", 2);

        bookingQueue.add(new BookingRequest(101, "A", "DELUXE"));
        bookingQueue.add(new BookingRequest(102, "B", "DELUXE"));
        bookingQueue.add(new BookingRequest(103, "C", "DELUXE"));

        Runnable task = () -> {
            while (true) {
                processBooking();
                if (bookingQueue.isEmpty()) break;
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Inventory: " + inventory);
        System.out.println("Booked Rooms: " + bookedRooms);
    }

    public static synchronized void processBooking() {

        if (bookingQueue.isEmpty()) return;

        BookingRequest request = bookingQueue.poll();

        if (request == null) return;

        String type = request.roomType;

        if (inventory.getOrDefault(type, 0) <= 0) {
            System.out.println("❌ No rooms available for " + request.guestName);
            return;
        }

        if (bookedRooms.contains(request.roomNo)) {
            System.out.println("❌ Room already booked: " + request.roomNo);
            return;
        }

        inventory.put(type, inventory.get(type) - 1);
        bookedRooms.add(request.roomNo);

        System.out.println("✅ Booked Room " + request.roomNo + " for " + request.guestName);
    }
}