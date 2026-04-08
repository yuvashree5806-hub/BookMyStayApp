import java.io.*;
import java.util.*;

class SystemState implements Serializable {
    HashMap<Integer, String> bookings;
    HashMap<String, Integer> inventory;

    SystemState(HashMap<Integer, String> bookings, HashMap<String, Integer> inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

public class BookMyStayApp {

    static HashMap<Integer, String> bookings = new HashMap<>();
    static HashMap<String, Integer> inventory = new HashMap<>();
    static final String FILE_NAME = "system_state.ser";

    public static void main(String[] args) {

        loadState();

        inventory.putIfAbsent("DELUXE", 2);

        bookings.put(101, "Yuva");
        inventory.put("DELUXE", inventory.get("DELUXE") - 1);

        saveState();

        System.out.println("Bookings: " + bookings);
        System.out.println("Inventory: " + inventory);
    }

    public static void saveState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            SystemState state = new SystemState(bookings, inventory);
            oos.writeObject(state);
            System.out.println("State saved");
        } catch (IOException e) {
            System.out.println("Error saving state");
        }
    }

    public static void loadState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous state found");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) ois.readObject();
            bookings = state.bookings;
            inventory = state.inventory;
            System.out.println("State restored");
        } catch (Exception e) {
            System.out.println("Error loading state, starting fresh");
        }
    }
}