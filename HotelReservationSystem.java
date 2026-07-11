import java.io.*;
import java.util.*;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Load rooms from rooms.txt
    public static void loadRooms() {
        try {
            File file = new File("rooms.txt");

            if (!file.exists()) {
                System.out.println("rooms.txt not found!");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                int roomNo = Integer.parseInt(data[0]);
                String category = data[1];
                double price = Double.parseDouble(data[2]);
                boolean available = Boolean.parseBoolean(data[3]);

                rooms.add(new Room(roomNo, category, price, available));
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading rooms.");
        }
    }

    // Display all available rooms
    public static void searchRooms() {

        System.out.println("\n===== Available Rooms =====");

        boolean found = false;

        for (Room room : rooms) {

            if (room.isAvailable()) {
                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    // Search by category
    public static void searchByCategory() {

        System.out.println("\nSelect Category");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String category = "";

        switch (choice) {
            case 1:
                category = "Standard";
                break;
            case 2:
                category = "Deluxe";
                break;
            case 3:
                category = "Suite";
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }

        boolean found = false;

        System.out.println("\nAvailable " + category + " Rooms");

        for (Room room : rooms) {

            if (room.isAvailable() &&
                    room.getCategory().equalsIgnoreCase(category)) {

                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    // Save updated rooms to file
    public static void saveRooms() {

        try {

            PrintWriter writer = new PrintWriter(new FileWriter("rooms.txt"));

            for (Room room : rooms) {
                writer.println(room.toString());
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving rooms.");
        }
    }
        // Book a room
    public static void bookRoom() {

        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        // Find the room
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available!");
            return;
        }

        // Customer details
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Check-In Date (dd-mm-yyyy): ");
        String checkIn = sc.nextLine();

        System.out.print("Enter Check-Out Date (dd-mm-yyyy): ");
        String checkOut = sc.nextLine();

        // Payment
        String paymentStatus = Payment.processPayment(selectedRoom.getPrice(), sc);

        if (!paymentStatus.equals("Paid")) {
            System.out.println("Booking Failed!");
            return;
        }

        // Create objects
        Customer customer = new Customer(name, phone);

        String reservationId = "R" + selectedRoom.getRoomNumber();

        Reservation reservation = new Reservation(
                reservationId,
                customer,
                selectedRoom,
                checkIn,
                checkOut,
                paymentStatus);

        // Mark room as booked
        selectedRoom.setAvailable(false);

        // Update rooms file
        saveRooms();

        // Save booking
        try {

            PrintWriter writer =
                    new PrintWriter(new FileWriter("bookings.txt", true));

            writer.println(reservation.toString());

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving booking.");
        }

        System.out.println("\nBooking Successful!");
        reservation.displayReservation();
    }
        // View all bookings
    public static void viewBookings() {

        try {
            File file = new File("bookings.txt");

            if (!file.exists()) {
                System.out.println("No bookings found.");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            System.out.println("\n===== Booking Details =====");

            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error reading bookings.");
        }
    }

    // Cancel reservation
    public static void cancelReservation() {

        System.out.print("Enter Room Number to Cancel: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        boolean found = false;

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNo) {

                if (!room.isAvailable()) {

                    room.setAvailable(true);
                    saveRooms();

                    System.out.println("Reservation Cancelled.");
                    System.out.println("Note: Booking record remains in bookings.txt");

                } else {
                    System.out.println("Room is already available.");
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Room not found.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        loadRooms();

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Rooms by Category");
            System.out.println("3. Book Room");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    searchByCategory();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
