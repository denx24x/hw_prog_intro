import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);
    public static int INF = (int)1e9;
    
    public static void subsolve(int x1, int y1, int x2, int y2, char[][] arr){
        for(int i = x1; i <= x2;i++){
            for(int g = y1; g <= y2;g++){
                if(arr[i][g] == '.') continue;
                for(int c = g - 1; c >= y1;c--){
                    if(arr[i][c] != '.') break;
                    arr[i][c] = Character.toLowerCase(arr[i][g]);
                }
                for(int c = g + 1; c <= y2;c++){
                    if(arr[i][c] != '.'){ 
                        g = c - 1;    
                        break; 
                    }
                    arr[i][c] = Character.toLowerCase(arr[i][g]);
                }
            }
        }
        for(int g = x1; g <= x2;g++){
            if(arr[g][y1] == '.') continue;
            for(int c = g - 1; c >= x1;c--){
                if(arr[c][y1] != '.') break;
                for(int k = y1; k <= y2;k++){
                    arr[c][k] = Character.toLowerCase(arr[g][k]);
                }
            }
            for(int c = g + 1; c <= x2;c++){
                if(arr[c][y1] != '.'){ 
                    g = c - 1;
                    break;
                }
                for(int k = y1; k <= y2;k++){
                    arr[c][k] = Character.toLowerCase(arr[g][k]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n, m;
        n = input.nextInt();
        m = input.nextInt();
        char[][] arr = new char[n][m];
        for(int i = 0; i < n;i++){
            String bf = input.next();
            for(int g = 0;g < m;g++){
                arr[i][g] = bf.charAt(g);
            }
        }

        int[] d = new int[m];
        int[] d1 = new int[m];
        int[] d2 = new int[m];
        Arrays.fill(d, -1);
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        int x1, x2, y1, y2, sx, sy;
        x1 = x2 = y1 = y2 = sx = sy = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                if(arr[i][j] == 'A'){
                    sx = i;
                    sy = j;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                if (arr[i][j] != '.' && arr[i][j] != 'A'){
                    d[j] = i;
                } 
            }
            while (!st.empty()) st.pop();
            for (int j = 0; j < m; j++) {
                while (!st.empty() && d[st.peek()] <= d[j])  st.pop();
                d1[j] = st.empty() ? -1 : st.peek();
                st.push (j);
            }
            while (!st.empty()) st.pop();
            for (int j = m - 1; j >= 0; j--) {
                while (!st.empty() && d[st.peek()] <= d[j])  st.pop();
                d2[j] = st.empty() ? m : st.peek();
                st.push (j);
            }
            for (int j=0; j<m; ++j){
                int dx1 = Math.min(d[j] + 1, i);
                int dx2 = Math.max(d[j] + 1, i);
                int dy1 = Math.min(d1[j] + 1, d2[j] - 1);
                int dy2 = Math.max(d1[j] + 1, d2[j] - 1);
                int bf = (i - d[j]) * (d2[j] - d1[j] - 1);
                if (dx1 <= sx && sx <= dx2 && dy1 <= sy && sy <= dy2 && bf > ans) {
                   ans = bf;
                   x1 = dx1;
                   x2 = dx2;
                   y1 = dy1;
                   y2 = dy2;
                }
                
            }
        }
        subsolve(x1, y1, x2, y2, arr);
        subsolve(0, 0, n - 1, y1 - 1, arr);
        subsolve(0, y1, x1 - 1, y2, arr);
        if (y2 + 1 != m) subsolve(0, y2 + 1, n - 1, m - 1, arr);
        if (x1 + 1 != n) subsolve(x1 + 1, y1, n - 1, y2, arr);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}