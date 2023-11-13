import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a, b, n;
        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        b = input.nextInt();
        n = input.nextInt();
        System.out.print(2 * ((n - a - 1) / (b - a)) + 1);
    }
}