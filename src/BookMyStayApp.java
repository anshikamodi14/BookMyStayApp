import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 * using HashMap as the single source of truth.
 *
 * Version: 3.1 (Refactored)
 *
 * @author Student
 * @version 3.1
 */

// Inventory management class
class RoomInventory {

    // HashMap to store room type and available count
    private HashMap<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Method to get availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Method to display full inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
import java.util.HashMap;
import java.util.Map;

    /**
     * UseCase3InventorySetup
     *
     * Demonstrates centralized room inventory management
     * using HashMap as the single source of truth.
     *
     * Version: 3.1 (Refactored)
     *
     * @author Student
     * @version 3.1
     */

// Inventory management class
    class RoomInventory {

        // HashMap to store room type and available count
        private HashMap<String, Integer> inventory;

        // Constructor initializes inventory
        public RoomInventory() {
            inventory = new HashMap<>();

            // Register room types with availability
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        // Method to get availability of a room type
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Method to update availability
        public void updateAvailability(String roomType, int newCount) {
            inventory.put(roomType, newCount);
        }

        // Method to display full inventory
        public void displayInventory() {
            System.out.println("\n--- Current Room Inventory ---");

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
            }
        }
    }

    // Main application class
    public class UseCase3InventorySetup {

        public static void main(String[] args) {

            System.out.println("=================================");
            System.out.println("     BOOK MY STAY SYSTEM v3.1    ");
            System.out.println(" Centralized Inventory Manager   ");
            System.out.println("=================================");

            // Initialize inventory
            RoomInventory inventory = new RoomInventory();

            // Display current inventory
            inventory.displayInventory();

            // Example availability lookup
            System.out.println("\nChecking availability for Single Room...");
            System.out.println("Available: " + inventory.getAvailability("Single Room"));

            // Update inventory
            System.out.println("\nUpdating Suite Room availability...");
            inventory.updateAvailability("Suite Room", 1);

            // Display updated inventory
            inventory.displayInventory();
        }
    }