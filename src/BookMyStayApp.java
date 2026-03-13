import java.util.*;

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        // Create Room Objects (Domain Model)
        Room single = new Room("Single", 2000, Arrays.asList("WiFi", "TV", "AC"));
        Room deluxe = new Room("Deluxe", 3500, Arrays.asList("WiFi", "TV", "AC", "Mini Bar"));
        Room suite = new Room("Suite", 6000, Arrays.asList("WiFi", "TV", "AC", "Mini Bar", "Jacuzzi"));

        // Store room details
        Map<String, Room> roomCatalog = new HashMap<>();
        roomCatalog.put(single.getRoomType(), single);
        roomCatalog.put(deluxe.getRoomType(), deluxe);
        roomCatalog.put(suite.getRoomType(), suite);

        // Inventory Setup (State Holder)
        Inventory inventory = new Inventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Deluxe", 3);
        inventory.addRoomType("Suite", 0); // No availability

        // Search Service
        SearchService searchService = new SearchService(inventory, roomCatalog);

        // Guest initiates search
        System.out.println("Available Rooms:");
        searchService.searchAvailableRooms();
    }
}


// Room Domain Model
class Room {

    private String roomType;
    private double pricePerNight;
    private List<String> amenities;

    public Room(String roomType, double pricePerNight, List<String> amenities) {
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: ₹" + pricePerNight);
        System.out.println("Amenities: " + amenities);
        System.out.println("-----------------------------");
    }
}


// Inventory Class (Centralized State Holder)
class Inventory {

    private Map<String, Integer> roomAvailability = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    // Read-only access
    public int getAvailableRooms(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return roomAvailability;
    }
}


// Search Service (Read-Only Logic)
class SearchService {

    private Inventory inventory;
    private Map<String, Room> roomCatalog;

    public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    // Room Search
    public void searchAvailableRooms() {

        Map<String, Integer> availability = inventory.getAllAvailability();

        for (String roomType : availability.keySet()) {

            int count = availability.get(roomType);

            // Validation Logic: Show only rooms with availability > 0
            if (count > 0) {

                Room room = roomCatalog.get(roomType);

                if (room != null) { // Defensive Programming
                    room.displayDetails();
                    System.out.println("Available Rooms: " + count);
                    System.out.println();
                }
            }
        }
    }
}