import java.util.Scanner;

public class Payment {

    public static String processPayment(double amount, Scanner sc) {

        System.out.println("\n===== Payment =====");
        System.out.println("Amount : ₹" + amount);

        System.out.println("Choose Payment Method");
        System.out.println("1. Card");
        System.out.println("2. UPI");
        System.out.println("3. Cash");

        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Payment Successful using Card.");
                break;

            case 2:
                System.out.println("Payment Successful using UPI.");
                break;

            case 3:
                System.out.println("Payment Successful using Cash.");
                break;

            default:
                System.out.println("Invalid Choice.");
                return "Failed";
        }

        return "Paid";
    }
}