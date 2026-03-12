import java.util.*;

/**
 * Service class represents an optional add-on service.
 */
class Service {

    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * AddOnServiceManager manages services attached to reservations.
 */
class AddOnServiceManager {

    // Map reservation ID -> List of services
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getName() + " added to reservation " + reservationId);
    }

    // Calculate additional cost
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service service : services) {
                total += service.getCost();
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        System.out.println("\nServices for Reservation " + reservationId);

        if (services != null) {
            for (Service service : services) {
                System.out.println(service.getName() + " - " + service.getCost());
            }
        }
    }
}

/**
 * Main Application
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        String reservationId = "RES101";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservationId, new Service("Breakfast", 500));
        manager.addService(reservationId, new Service("Airport Pickup", 1200));
        manager.addService(reservationId, new Service("Spa Access", 800));

        // Display services
        manager.displayServices(reservationId);

        // Calculate total add-on cost
        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: " + totalCost);
    }
}