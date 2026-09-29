import java.util.*;

class Room {
    int number;
    String type;
    double price;
    boolean booked;
    String guestName;

    Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.booked = false;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
    }

    static void viewRooms() {
        System.out.println("\n----- ROOM DETAILS -----");
        for (Room r : rooms) {
            String status = r.booked ? "Booked by " + r.guestName : "Available";
            System.out.printf("Room %d | %-8s | Rs. %.2f/night | %s%n",
                    r.number, r.type, r.price, status);
        }
    }

    static void bookRoom() {
        System.out.print("Enter room number: ");
        int number = Integer.parseInt(sc.nextLine());

        for (Room r : rooms) {
            if (r.number == number) {
                if (r.booked) {
                    System.out.println("Room is already booked.");
                    return;
                }

                System.out.print("Enter guest name: ");
                r.guestName = sc.nextLine();

                System.out.print("Enter number of nights: ");
                int nights = Integer.parseInt(sc.nextLine());
                while (nights <= 0) {
                    System.out.print("Enter a valid number of nights: ");
                    nights = Integer.parseInt(sc.nextLine());
                }

                r.booked = true;
                double total = r.price * nights;

                System.out.println("Booking successful!");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room: " + r.number + " (" + r.type + ")");
                System.out.println("Total amount: Rs. " + total);
                return;
            }
        }
        System.out.println("Room not found.");
    }

    static void cancelBooking() {
        System.out.print("Enter room number to cancel: ");
        int number = Integer.parseInt(sc.nextLine());

        for (Room r : rooms) {
            if (r.number == number) {
                if (!r.booked) {
                    System.out.println("This room is not booked.");
                    return;
                }
                r.booked = false;
                r.guestName = null;
                System.out.println("Booking cancelled successfully.");
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> viewRooms();
                case "2" -> bookRoom();
                case "3" -> cancelBooking();
                case "4" -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
