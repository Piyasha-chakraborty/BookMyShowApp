import java.util.LinkedList;
import java.util.Queue;

public class BookMyShowApp {

    static class Reservation {

        private String guestName;
        private String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        String getGuestName() {
            return guestName;
        }

        String getRoomType() {
            return roomType;
        }
    }

    static class BookingRequestQueue {

        private Queue<Reservation> requestQueue;

        BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        Reservation getNextRequest() {
            return requestQueue.poll();
        }

        boolean hasPendingRequests() {
            return !requestQueue.isEmpty();
        }
    }

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vannathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {

            Reservation r = bookingQueue.getNextRequest();

            System.out.println("Processing booking for Guest: "
                    + r.getGuestName()
                    + ", Room Type: "
                    + r.getRoomType());
        }
    }
}