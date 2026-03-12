import java.util.LinkedList;
import java.util.Queue;

/**
 * Reservation class represents a guest booking request.
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest : " + guestName);
        System.out.println("Requested Room : " + roomType);
    }
}

/**
 * BookingQueue manages incoming reservation requests using FIFO queue.
 */
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addReservation(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Reservation added for " + reservation.getGuestName());
    }

    // Display queued requests
    public void displayQueue() {
        System.out.println("\nCurrent Booking Requests:\n");

        for (Reservation reservation : queue) {
            reservation.displayReservation();
            System.out.println();
        }
    }
}

/**
 * Application entry point
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Guests submit booking requests
        bookingQueue.addReservation(new Reservation("Alice", "Single Room"));
        bookingQueue.addReservation(new Reservation("Bob", "Double Room"));
        bookingQueue.addReservation(new Reservation("Charlie", "Suite Room"));

        // Display queue
        bookingQueue.displayQueue();
    }
}