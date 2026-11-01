a
import java.util.Scanner;

public class SimpleReflexAgent {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Simple Reflex Agent - Vacuum Cleaner");

        System.out.print("Enter location (A or B): ");
        char location = sc.next().charAt(0);

        System.out.print("Is Room A Dirty? (yes/no): ");
        String roomA = sc.next();

        System.out.print("Is Room B Dirty? (yes/no): ");
        String roomB = sc.next();

        if (location == 'A' || location == 'a') {

            if (roomA.equalsIgnoreCase("yes")) {
                System.out.println("Action: SUCK");
                roomA = "no";
            } else {
                System.out.println("Action: MOVE RIGHT");
            }

        } else if (location == 'B' || location == 'b') {

            if (roomB.equalsIgnoreCase("yes")) {
                System.out.println("Action: SUCK");
                roomB = "no";
            } else {
                System.out.println("Action: MOVE LEFT");
            }

        } else {
            System.out.println("Invalid location!");
        }

        System.out.println("\nFinal Room Status:");
        System.out.println("Room A: " + roomA);
        System.out.println("Room B: " + roomB);

        sc.close();
    }
}
