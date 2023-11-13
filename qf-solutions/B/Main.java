import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n;
        n = input.nextInt();
        int start = -710 * 25000;
        double step = Math.PI * 2 + 0.00006;
        for(int i = 0; i < n;i ++){
            double ssin = Math.sin(start);
            for(int g = 710; ;g++){
                double gsin = Math.sin(start + g);
                if(ssin <= gsin){
                    start += g;
                    System.out.println(start);
                    break;
                }
            }
        }
    }
}