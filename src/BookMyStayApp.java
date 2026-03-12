import java.util.HashMap;

/**
 * Room domain model
 */
class Room {

    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Price     : " + price);
    }
}

/**
 * Inventory class – stores availability using HashMap
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/**
 * Search service – read-only operations
 */
class SearchService {

    public void searchRooms(RoomInventory inventory, Room[] rooms) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getType());

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println();
            }
        }
    }
}

/**
 * Application entry point
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new Room("Single Room", 2000),
                new Room("Double Room", 3500),
                new Room("Suite Room", 6000)
        };

        SearchService searchService = new SearchService();

        searchService.searchRooms(inventory, rooms);
    }
}