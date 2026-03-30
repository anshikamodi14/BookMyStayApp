import java.util.*;

// Represents a basic reservation (already created in previous use cases)
class Reservation {
    private String reservationId;
    private String guestName;

    public Reservation(String reservationId, String guestName) {
        this.reservationId = reservationId;
        this.guestName = guestName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", guestName='" + guestName + '\'' +
                '}';
    }
}

// Represents an Add-On Service
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Manages mapping between Reservation and its Add-On Services
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add a service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service " + service + " to Reservation ID: " + reservationId);
    }

    // View services for a reservation
    public void viewServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for Reservation ID: " + reservationId);
            return;
        }

        System.out.println("\nAdd-On Services for Reservation ID: " + reservationId);
        for (AddOnService service : services) {
            System.out.println(service);
        }
    }

    // Calculate total cost of add-on services
    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0.0;

        double total = 0.0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }
}

// Main class
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        // Sample reservations (assume already allocated in Use Case 6)
        Reservation r1 = new Reservation("R101", "Arun");
        Reservation r2 = new Reservation("R102", "Priya");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Define available services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200);
        AddOnService extraBed = new AddOnService("Extra Bed", 800);
        AddOnService spa = new AddOnService("Spa Access", 1500);

        // Guest selects services
        manager.addService(r1.getReservationId(), breakfast);
        manager.addService(r1.getReservationId(), spa);

        manager.addService(r2.getReservationId(), airportPickup);
        manager.addService(r2.getReservationId(), extraBed);
        manager.addService(r2.getReservationId(), breakfast);

        // View services
        manager.viewServices(r1.getReservationId());
        manager.viewServices(r2.getReservationId());

        // Calculate cost
        System.out.println("\nTotal Add-On Cost for " + r1.getReservationId() +
                ": ₹" + manager.calculateTotalServiceCost(r1.getReservationId()));

        System.out.println("Total Add-On Cost for " + r2.getReservationId() +
                ": ₹" + manager.calculateTotalServiceCost(r2.getReservationId()));
    }
}}