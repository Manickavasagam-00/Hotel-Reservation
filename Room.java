public class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    // Constructor
    public Room(int roomNumber, String category, double price, boolean available) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setter
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Display Room Details
    public void displayRoom() {
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Category    : " + category);
        System.out.println("Price       : ₹" + price);
        System.out.println("Status      : " + (available ? "Available" : "Booked"));
        System.out.println("------------------------------");
    }

    // Save room details to file format
    @Override
    public String toString() {
        return roomNumber + "," + category + "," + price + "," + available;
    }
}