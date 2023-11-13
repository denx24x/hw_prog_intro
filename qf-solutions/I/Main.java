import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static int INF = (int)1e9;

    public static void main(String[] args) {
        int n;
        n = input.nextInt();
        int[][] arr = new int [n][3];
        for(int i = 0;i < n;i++){
            for(int g = 0; g < 3;g++){
                arr[i][g] = input.nextInt();
            }
        }
        int xl = INF;
        int xr = -INF;
        int yl = INF;
        int yr = -INF;
        for(int i = 0; i < n;i++){
            xl = Math.min(xl, arr[i][0] - arr[i][2]);
            xr = Math.max(xr, arr[i][0] + arr[i][2]);
            yl = Math.min(yl, arr[i][1] - arr[i][2]);
            yr = Math.max(yr, arr[i][1] + arr[i][2]);
        }
        int h = Math.max(xr - xl, yr - yl);
        System.out.println((xl + xr) / 2 + " " + (yl + yr) / 2 + " " + (h / 2 + h % 2));
    }
}