import java.util.Scanner;

public class NumberGuessingAgent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int low = 1, high = 100;
        System.out.println("Think of a number between 1 and 100.");
        System.out.println("Enter H if my guess is too high, L if too low, and C if correct.");

        while (low <= high) {
            int guess = low + (high - low) / 2;
            System.out.println("My guess is: " + guess);
            System.out.print("Your response (H/L/C): ");
            String response = sc.next().trim().toUpperCase();

            if (response.equals("C")) {
                System.out.println("Great! I guessed your number.");
                sc.close();
                return;
            } else if (response.equals("H")) {
                high = guess - 1;
            } else if (response.equals("L")) {
                low = guess + 1;
            } else {
                System.out.println("Please enter only H, L, or C.");
            }
        }

        System.out.println("Your answers were inconsistent. Please try again.");
        sc.close();
    }
}
