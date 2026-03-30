import java.util.*;

// Custom exception for invalid booking inputs
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String reservationId, String guestName, String roomType, int nights) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
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

    public int getNights() {
        return nights;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID='" + reservationId + '\'' +
                ", Guest='" + guestName + '\'' +
                ", Room='" + roomType + '\'' +
                ", Nights=" + nights +
                '}';
    }
}

// Inventory manager with validation
class RoomInventory {
    private Map<String, Integer> availableRooms;

    public RoomInventory() {
        availableRooms = new HashMap<>();
        availableRooms.put("Standard", 5);
        availableRooms.put("Deluxe", 3);
        availableRooms.put("Suite", 2);
    }

    // Validate room type
    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!availableRooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    // Check availability
    public void checkAvailability(String roomType, int nights) throws InvalidBookingException {
        int count = availableRooms.getOrDefault(roomType, 0);
        if (count <= 0) {
            throw new InvalidBookingException("No available rooms for type: " + roomType);
        }
        if (nights <= 0) {
            throw new InvalidBookingException("Number of nights must be positive.");
        }
    }

    // Reserve room
    public void reserveRoom(String roomType) throws InvalidBookingException {
        int count = availableRooms.get(roomType);
        if (count <= 0) {
            throw new InvalidBookingException("Cannot reserve room: " + roomType + " is sold out.");
        }
        availableRooms.put(roomType, count - 1);
        System.out.println("Room reserved: " + roomType + " (Remaining: " + (count - 1) + ")");
    }

    // Display current inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : availableRooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " room(s) available");
        }
    }
}

// Booking service with validation
class BookingService {
    private RoomInventory inventory;
    private List<Reservation> confirmedBookings;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        confirmedBookings = new ArrayList<>();
    }

    // Attempt to book a room with validation
    public void bookRoom(String reservationId, String guestName, String roomType, int nights) {
        try {
            inventory.validateRoomType(roomType);
            inventory.checkAvailability(roomType, nights);
            inventory.reserveRoom(roomType);

            Reservation reservation = new Reservation(reservationId, guestName, roomType, nights);
            confirmedBookings.add(reservation);

            System.out.println("Booking confirmed: " + reservation);

        } catch (InvalidBookingException e) {
            System.err.println("Booking failed: " + e.getMessage());
        }
    }

    public void viewConfirmedBookings() {
        System.out.println("\n--- Confirmed Bookings ---");
        if (confirmedBookings.isEmpty()) {
            System.out.println("No bookings confirmed yet.");
            return;
        }
        for (Reservation r : confirmedBookings) {
            System.out.println(r);
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        // Display initial inventory
        inventory.displayInventory();

        // Simulate guest booking attempts
        bookingService.bookRoom("R101", "Arun", "Deluxe", 2);
        bookingService.bookRoom("R102", "Priya", "Suite", 3);
        bookingService.bookRoom("R103", "Karthik", "Penthouse", 1); // Invalid room type
        bookingService.bookRoom("R104", "Meena", "Standard", 0);      // Invalid nights
        bookingService.bookRoom("R105", "Rahul", "Suite", 2);
        bookingService.bookRoom("R106", "Sita", "Suite", 1);          // Exceeding inventory

        // View final inventory and confirmed bookings
        inventory.displayInventory();
        bookingService.viewConfirmedBookings();
    }
}