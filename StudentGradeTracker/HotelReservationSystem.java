import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean available;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void reserve() {
        this.available = false;
    }

    public void release() {
        this.available = true;
    }
}

class Reservation {
    private int roomNumber;
    private String customerName;

    public Reservation(int roomNumber, String customerName) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Customer Name: " + customerName;
    }
}

public class HotelReservationSystem {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(201, "Suite"));
        rooms.add(new Room(202, "Deluxe"));
    }

    public void searchAvailableRooms(String category) {
        System.out.println("Available rooms in category " + category + ":");
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Room Number: " + room.getRoomNumber());
            }
        }
    }

    public void makeReservation(int roomNumber, String customerName) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.reserve();
                reservations.add(new Reservation(roomNumber, customerName));
                System.out.println("Reservation made successfully!");
                return;
            }
        }
        System.out.println("Room not available for reservation.");
    }

    public void viewReservations() {
        System.out.println("Current Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelReservationSystem hotel = new HotelReservationSystem();

        while (true) {
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single, Double, Suite, Deluxe): ");
                    String category = scanner.nextLine();
                    hotel.searchAvailableRooms(category);
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    hotel.makeReservation(roomNumber, customerName);
                    break;
                case 3:
                    hotel.viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
