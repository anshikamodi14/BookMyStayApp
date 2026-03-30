import java.util.*;

// Basic Reservation class
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

// Booking History stores confirmed reservations
class BookingHistory {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new ArrayList<>();
    }

    // Add confirmed booking to history
    public void addBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
        System.out.println("Booking confirmed and added to history: " + reservation);
    }

    // Retrieve all confirmed bookings
    public List<Reservation> getAllBookings() {
        return Collections.unmodifiableList(confirmedBookings);
    }
}

// Reporting Service generates summaries without modifying booking history
class BookingReportService {

    // Display all confirmed bookings
    public void displayAllBookings(List<Reservation> bookings) {
        System.out.println("\n--- All Confirmed Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Reservation r : bookings) {
            System.out.println(r);
        }
    }

    // Generate a summary report (count per room type)
    public void generateRoomTypeSummary(List<Reservation> bookings) {
        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : bookings) {
            roomTypeCount.put(r.getRoomType(), roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("\n--- Room Type Summary ---");
        for (Map.Entry<String, Integer> entry : roomTypeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " booking(s)");
        }
    }
}

// Main class
public class UseCase8BookingHistoryReporting {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("R101", "Arun", "Deluxe", 2);
        Reservation r2 = new Reservation("R102", "Priya", "Suite", 3);
        Reservation r3 = new Reservation("R103", "Karthik", "Standard", 1);
        Reservation r4 = new Reservation("R104", "Meena", "Deluxe", 4);

        // Add bookings to history
        history.addBooking(r1);
        history.addBooking(r2);
        history.addBooking(r3);
        history.addBooking(r4);

        // Admin requests
        List<Reservation> allBookings = history.getAllBookings();

        // Display all bookings
        reportService.displayAllBookings(allBookings);

        // Generate room type summary
        reportService.generateRoomTypeSummary(allBookings);
    }
}