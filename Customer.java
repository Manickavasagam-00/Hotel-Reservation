public class Customer {
    private String customerName;
    private String phone;

    // Constructor
    public Customer(String customerName, String phone) {
        this.customerName = customerName;
        this.phone = phone;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    // Display customer details
    public void displayCustomer() {
        System.out.println("Customer Name : " + customerName);
        System.out.println("Phone         : " + phone);
    }
}
