import java.util.Scanner;

public class Main {
    public static 

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] arr = new int [n][n];
        int[][] ans = new int [n][n];
        
        for (int i = 0; i < n;i++) {
            String v = input.next();
            for (int g = 0; g < n;g++) {
                arr[i][g] = Character.getNumericValue(v.charAt(g));
            }
        }

        for (int i = 0; i < n;i++) {
            for (int g = 0; g < n;g++) {
                if (arr[i][g] == 0) {
                    ans[i][g] = 0;
                    continue;
                }
                ans[i][g] = 1;
                for (int k = g + 1; k < n;k++) {
                    arr[i][k] = (arr[i][k] - arr[g][k]) % 10;
                }
            }
        }
        
        for (int i = 0; i < n;i++) {
            for (int g = 0; g < n;g++) {
                System.out.print(ans[i][g]);
            }
            System.out.println();
        }
    }
}