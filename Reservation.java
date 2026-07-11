public class Reservation {
    private String reservationId;
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private String paymentStatus;

    // Constructor
    public Reservation(String reservationId, Customer customer, Room room,
                       String checkInDate, String checkOutDate,
                       String paymentStatus) {

        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentStatus = paymentStatus;
    }

    // Getter
    public String getReservationId() {
        return reservationId;
    }

    // Display reservation details
    public void displayReservation() {
        System.out.println("\n===== Booking Details =====");
        System.out.println("Reservation ID : " + reservationId);
        customer.displayCustomer();
        room.displayRoom();
        System.out.println("Check-In Date  : " + checkInDate);
        System.out.println("Check-Out Date : " + checkOutDate);
        System.out.println("Payment Status : " + paymentStatus);
    }

    // Save booking to file
    @Override
    public String toString() {
        return reservationId + "," +
               customer.getCustomerName() + "," +
               customer.getPhone() + "," +
               room.getRoomNumber() + "," +
               room.getCategory() + "," +
               checkInDate + "," +
               checkOutDate + "," +
               room.getPrice() + "," +
               paymentStatus;
    }
}