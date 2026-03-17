import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {

    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 2);
        rooms.put("Suite", 1);
    }

    public boolean allocateRoom(String type) {
        int count = rooms.getOrDefault(type, 0);
        if (count > 0) {
            rooms.put(type, count - 1);
            return true;
        }
        return false;
    }

    public void printInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

class RoomAllocationService {

    private Map<String, Integer> roomCounter = new HashMap<>();

    public void allocateRoom(Reservation r, RoomInventory inventory) {

        if (inventory.allocateRoom(r.roomType)) {

            int count = roomCounter.getOrDefault(r.roomType, 0) + 1;
            roomCounter.put(r.roomType, count);

            String roomId = r.roomType + "-" + count;

            System.out.println("Booking confirmed for Guest: "
                    + r.guestName + ", Room ID: " + roomId);
        } else {
            System.out.println("No rooms available for " + r.guestName);
        }
    }
}

class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    public void run() {

        while (true) {

            Reservation reservation;

            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) break;
                reservation = bookingQueue.getRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}

public class BookMyShowApp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Vannathi", "Double"));
        queue.addRequest(new Reservation("Kunal", "Suite"));
        queue.addRequest(new Reservation("Subha", "Single"));

        Thread t1 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(queue, inventory, service));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        inventory.printInventory();
    }
}
