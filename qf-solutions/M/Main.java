import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void solve(){
        int n = input.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n;i++){
            arr[i] = input.nextInt();
        }

        Map<Integer, Integer> count = new HashMap<>();
        int answer = 0;
        for(int j = n - 1; j >= 0;j--){
            for(int i = 0; i < j;i++){
                answer += count.getOrDefault(2 * arr[j] - arr[i], 0);
            }
            count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        int t;
        t = input.nextInt();
        for(int i = 0; i < t;i++){
            solve();
        }
    }
}