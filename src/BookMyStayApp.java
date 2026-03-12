import java.util.*;

/**
 * Reservation class represents a confirmed booking.
 */
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println();
    }
}

/**
 * BookingHistory stores confirmed reservations.
 */
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Retrieve stored reservations
    public List<Reservation> getReservations() {
        return history;
    }
}

/**
 * BookingReportService generates reports from booking history.
 */
class BookingReportService {

    public void generateReport(List<Reservation> reservations) {

        System.out.println("=== Booking History Report ===\n");

        for (Reservation r : reservations) {
            r.display();
        }

        System.out.println("Total Reservations: " + reservations.size());
    }
}

/**
 * Main Application
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("R101", "Alice", "Single"));
        history.addReservation(new Reservation("R102", "Bob", "Double"));
        history.addReservation(new Reservation("R103", "Charlie", "Suite"));

        // Admin requests report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getReservations());
    }
}