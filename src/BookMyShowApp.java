import java.util.HashMap;
import java.util.Map;

public class BookMyShowApp {

    static abstract class Room {

        int beds;
        int size;
        double price;

        Room(int beds, int size, double price) {
            this.beds = beds;
            this.size = size;
            this.price = price;
        }

        void displayDetails() {
            System.out.println("Beds: " + beds);
            System.out.println("Size: " + size + " sqft");
            System.out.println("Price per night: " + price);
        }
    }

    static class SingleRoom extends Room {
        SingleRoom() {
            super(1, 250, 1500.0);
        }
    }

    static class DoubleRoom extends Room {
        DoubleRoom() {
            super(2, 400, 2500.0);
        }
    }

    static class SuiteRoom extends Room {
        SuiteRoom() {
            super(3, 750, 5000.0);
        }
    }

    static class RoomInventory {

        Map<String, Integer> availability;

        RoomInventory() {
            availability = new HashMap<>();
            availability.put("Single", 5);
            availability.put("Double", 3);
            availability.put("Suite", 2);
        }

        Map<String, Integer> getAvailability() {
            return availability;
        }
    }

    static class RoomSearchService {

        void searchAvailableRooms(RoomInventory inventory,
                                  Room singleRoom,
                                  Room doubleRoom,
                                  Room suiteRoom) {

            Map<String, Integer> availability = inventory.getAvailability();

            System.out.println("Room Search\n");

            if (availability.get("Single") > 0) {
                System.out.println("Single Room:");
                singleRoom.displayDetails();
                System.out.println("Available: " + availability.get("Single"));
                System.out.println();
            }

            if (availability.get("Double") > 0) {
                System.out.println("Double Room:");
                doubleRoom.displayDetails();
                System.out.println("Available: " + availability.get("Double"));
                System.out.println();
            }

            if (availability.get("Suite") > 0) {
                System.out.println("Suite Room:");
                suiteRoom.displayDetails();
                System.out.println("Available: " + availability.get("Suite"));
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(inventory, single, dbl, suite);
    }
}