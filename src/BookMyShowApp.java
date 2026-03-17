import java.util.*;

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {

    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 2);
        rooms.put("Double", 2);
        rooms.put("Suite", 1);
    }

    public boolean isValidRoomType(String roomType) {
        return rooms.containsKey(roomType);
    }

    public int getAvailableRooms(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }
}

class ReservationValidator {

    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.getAvailableRooms(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}

class BookingRequestQueue {

    private Queue<String> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(String request) {
        queue.add(request);
    }

    public String processRequest() {
        return queue.poll();
    }
}

public class BookMyShowApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue queue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            queue.addRequest(guestName + " - " + roomType);

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {

            scanner.close();
        }
    }
}