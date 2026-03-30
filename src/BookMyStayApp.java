import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Reservation class representing a booking request
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

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// Main application class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Queue to store booking requests (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Book My Stay App: Booking Request Queue ===");

        // Accept number of requests
        System.out.print("Enter number of booking requests: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Input booking requests
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for request " + (i + 1));

            System.out.print("Guest Name: ");
            String name = scanner.nextLine();

            System.out.print("Room Type: ");
            String roomType = scanner.nextLine();

            // Create reservation and add to queue
            Reservation reservation = new Reservation(name, roomType);
            bookingQueue.add(reservation);

            System.out.println("Booking request added to queue.");
        }

        // Display queue (no processing, no allocation)
        System.out.println("\n=== Booking Requests in Queue (FIFO Order) ===");
        for (Reservation r : bookingQueue) {
            System.out.println(r);
        }

        System.out.println("\nTotal requests in queue: " + bookingQueue.size());

        scanner.close();
    }
}