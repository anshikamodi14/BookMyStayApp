import java.util.LinkedList;
import java.util.Queue;

// Reservation class representing a booking request
class Reservation {
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
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
                "guestName='" + guestName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", nights=" + nights +
                '}';
    }
}

// Booking Request Queue Manager
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request to queue (FIFO)
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added to queue: " + reservation);
    }

    // View all pending requests
    public void viewRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        System.out.println("\n--- Booking Request Queue (FIFO Order) ---");
        for (Reservation r : requestQueue) {
            System.out.println(r);
        }
    }

    // Fetch next request (for future processing)
    public Reservation getNextRequest() {
        return requestQueue.peek(); // Does NOT remove
    }

    // Remove next request (used in allocation stage later)
    public Reservation processNextRequest() {
        return requestQueue.poll(); // Removes from queue
    }
}

// Main class
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        // Simulating multiple guest requests (arrival order matters)
        queue.addRequest(new Reservation("Arun", "Deluxe", 2));
        queue.addRequest(new Reservation("Priya", "Suite", 3));
        queue.addRequest(new Reservation("Karthik", "Standard", 1));
        queue.addRequest(new Reservation("Meena", "Deluxe", 4));

        // View queued requests
        queue.viewRequests();

        // Show next request (without removing)
        System.out.println("\nNext request to be processed (peek):");
        System.out.println(queue.getNextRequest());

        // Demonstrate FIFO processing
        System.out.println("\nProcessing requests in FIFO order:");
        while (queue.getNextRequest() != null) {
            Reservation processed = queue.processNextRequest();
            System.out.println("Processing: " + processed);
        }

        // Final state
        queue.viewRequests();
    }
}