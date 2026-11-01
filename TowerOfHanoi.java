import java.util.Scanner;

public class TowerOfHanoi {
    static void moveDisks(int n, char source, char auxiliary, char destination) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }
        moveDisks(n - 1, source, destination, auxiliary);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        moveDisks(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of disks must be positive.");
        } else {
            System.out.println("Steps to solve Tower of Hanoi:");
            moveDisks(n, 'A', 'B', 'C');
            System.out.println("Total moves = " + ((1L << n) - 1));
        }
        sc.close();
    }
}
