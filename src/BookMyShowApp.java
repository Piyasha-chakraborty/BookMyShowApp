import java.util.HashMap;
import java.util.Map;

public class BookMyShowApp {

    static abstract class Room {

        protected int beds;
        protected int size;
        protected double price;

        public Room(int beds, int size, double price) {
            this.beds = beds;
            this.size = size;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Beds: " + beds);
            System.out.println("Size: " + size + " sqft");
            System.out.println("Price per night: " + price);
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 250, 1500.0);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 2500.0);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super(3, 750, 5000.0);
        }
    }

    static class RoomInventory {

        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            roomAvailability.put("Single", 5);
            roomAvailability.put("Double", 3);
            roomAvailability.put("Suite", 2);
        }

        public Map<String, Integer> getAvailability() {
            return roomAvailability;
        }

        public void updateAvailability(String type, int count) {
            roomAvailability.put(type, count);
        }
    }

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("Hotel Room Inventory Status\n");

        System.out.println("Single Room:");
        single.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability().get("Single"));

        System.out.println("\nDouble Room:");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability().get("Double"));

        System.out.println("\nSuite Room:");
        suite.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability().get("Suite"));
    }
}