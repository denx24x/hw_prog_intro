import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static int INF = (int)1e9;

    public static void main(String[] args) {
        int n;
        n = input.nextInt();
        int[] arr = new int [n];
        int[] prefix = new int[n];
        int a = 0;
        for(int i = 0; i < n;i++){
            arr[i] = input.nextInt();
            a = Math.max(a, arr[i]);
            if(i > 0){ 
                prefix[i] = prefix[i - 1] + arr[i];
            }else{
                prefix[i] = arr[i];
            }
        }

        int[] answer = new int[prefix[n - 1] + 1];
        int[] f = new int[prefix[n - 1] + 1];
        int sm = 0;
        int mx = a;
        for(int i = 0; i < n;i++){
            sm += arr[i];
            while(a < sm){
                f[a] = i - 1;
                a++;
            }
        }
        f[a] = n - 1;
        for(int t = mx; t <= a;t++){
            int start = 0;
            int ans = 1; 
            while(start + t < a){
                start = prefix[f[start + t]];
                ans++;
            }
            answer[t] = ans;
        }
        int m = input.nextInt();
        for(int i = 0; i < m;i++){
            int q = input.nextInt();
            if(q < mx){
                System.out.println("Impossible");
            }else{
                System.out.println(answer[q]);
            }
        }
    }
}