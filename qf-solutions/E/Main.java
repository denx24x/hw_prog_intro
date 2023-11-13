import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.Reader;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


public class Main {
    public static Scanner input = new Scanner(System.in);
    public static int INF = (int)1e9;
    public static Map<Integer, ArrayList<Integer>> path = new HashMap<>();
    public static int[] visited;
    public static int[] from;
    
    public static void subdfs(int v){
        for(int g : path.get(v)){
            if(visited[g] > 0){
                continue;
            }
            visited[g] = visited[v] + 1;
            from[g] = v;
            subdfs(g);
        }
    }

    public static void dfs(int v){
        Arrays.fill(visited, 0);
        visited[v] = 1;
        from[v] = -1;
        subdfs(v);
    }   

    public static void main(String[] args) {
        int n, m;
        DefaultFilter filter = new DefaultFilter();
        n = input.nextInt(filter);
        if(n == 1){
            System.out.println("YES");
            System.out.println(1);
            return;
        }
        m = input.nextInt(filter);
        visited = new int[n];
        from = new int[n];
        for(int i = 0; i < n - 1;i++){
            int a = input.nextInt(filter) - 1;
            int b = input.nextInt(filter) - 1;
            if(!path.containsKey(a)){
                path.put(a, new ArrayList<Integer>());
            }
            if(!path.containsKey(b)){
                path.put(b, new ArrayList<Integer>());
            }
            path.get(a).add(b);
            path.get(b).add(a);
        }
        int[] teams = new int[m];
        for(int i = 0; i < m;i++){
            teams[i] = input.nextInt(filter) - 1;
        }
        dfs(teams[0]);
        int mx = 0;
        int mxv = -1;
        for(int i = 0 ;i < m;i++){
            if(visited[teams[i]] - 1 >= mx){
                mx = visited[teams[i]] - 1;
                mxv = i;
            }
        }
        if(mx % 2 != 0){
            System.out.println("NO");
            return;
        }
        int start = teams[mxv];
        for(int g = 0; g < mx / 2;g++){
            start = from[start];
        }
        int mn = n + 10;
        mx = -1;
        dfs(start);
        for(int g = 0; g < m;g++){
            mx = Math.max(mx, visited[teams[g]] - 1);
            mn = Math.min(mn, visited[teams[g]] - 1);
        }
        if(mx == mn){
            System.out.println("YES");
            System.out.println(start + 1);
        }else{
            System.out.println("NO");
        }
    }
}

class IntPair {
    int first;
    int second;
    
    IntPair (int a, int b) {
        first = a;
        second = b;
    }
}

class Scanner{
    private final Reader input;
    private StringBuilder buffer = new StringBuilder();
    private boolean canRead = true;
    private boolean closed = false;
    private String lineSeparator = System.lineSeparator();
    private final int BLOCK_SIZE = 16384;
    private IOException inputException = null;
    private char[] readBuffer = new char[BLOCK_SIZE];

    public Scanner(Reader in) {
        input = in;
    }

    public Scanner(String in) {
        input = new StringReader(in);
    }

    public Scanner(InputStream in) {
        input = new InputStreamReader(in);
    }

    public boolean isClosed() {
        return closed;
    }
    public boolean hasException() {
        return inputException != null;
    }

    public IOException getException() {
        return inputException;
    }

    private void read() {
        if (!canRead) return;
        try {
            int readLength = input.read(readBuffer);
            if (readLength == -1) {
                canRead = false;
                return;
            }
            buffer.append(Arrays.copyOf(readBuffer, readLength));
        } catch(IOException ex) {
            canRead = false;
            inputException = ex;
        }    
    }

    public boolean hasNext(ScannerFilter filter) throws IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        return getNext(filter).first != -1;
    }

    private IntPair getNext(ScannerFilter filter) {
        while (true) {
            int pos = -1;
            for (int i = 0; i <= buffer.length();i++) {
                if(!canRead && i == buffer.length() && i - pos > 1){
                    return new IntPair(pos + 1, i);
                }
                if (i < buffer.length() && !filter.check(buffer.charAt(i))) {
                    if (i - pos != 1) {
                        return new IntPair(pos + 1, i);
                    }
                    pos = i;
                }
            }
            if (!canRead) {
                break;
            }
            read();
        }
        return new IntPair(-1, -1);
    }

    public String next(ScannerFilter filter) throws NoSuchElementException, IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        IntPair pos = getNext(filter);
        if (pos.first == -1) {
            throw new NoSuchElementException("input has not more values");
        }
        String res = buffer.substring(pos.first, pos.second);
        buffer.delete(0, pos.second);
        return res;
    }

    private int parseInt(String val) {
        int sign = val.startsWith("-") ? 1 : 0;
        int hex = val.regionMatches(true, sign, "0x", 0, 2) ? 1 : 0;
        String num = val.substring(sign + hex * 2, val.length());
        if (hex == 1) {
            return Integer.parseUnsignedInt(num, 16) * (sign == 1 ? -1 : 1);
        }

        final char[] chars = num.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                chars[i] += '0' - 'a';
            }
        }
        return Integer.parseUnsignedInt(new String(chars)) * (sign == 1 ? -1 : 1);
    }

    private boolean checkInt(String val){
        int sign = val.startsWith("-") ? 1 : 0;
        int hex = val.regionMatches(true, sign, "0x", 0, 2) ? 1 : 0;
        for(int i = sign + hex * 2; i < val.length();i++){
            char c = val.charAt(i);
            if(!Character.isDigit(c) && !Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isNextOnNewLine(ScannerFilter filter){
        if(closed) throw new IllegalStateException("Scanner is closed");
        IntPair pos = getNext(filter);
        if (pos.first == -1) {
            throw new NoSuchElementException("input has not more values");
        }
        return buffer.substring(0, pos.first).contains(lineSeparator);
    }

    public boolean hasNextInt(ScannerFilter filter) throws IllegalStateException {
        if (closed) throw new IllegalStateException("Scanner is closed");
        IntPair token = getNext(filter);
        if (token.first == -1) {
            return false;
        }
        String num = buffer.substring(token.first, token.second);
        return checkInt(num);
    }

    public boolean hasNextLine() throws IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        if (buffer.length() > 0) {
            return true;
        } else if(canRead) {
            read();
            return buffer.length() > 0;
        }
        return false;
    }

    public String nextLine() throws NoSuchElementException, IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        while (canRead && buffer.indexOf(lineSeparator) == -1) {
            read();
        }
        if (buffer.length() == 0) {
            throw new NoSuchElementException("nothing to read");
        }
        String res;
        int pos = buffer.indexOf(lineSeparator);
        if (pos == -1) {
            res = buffer.toString();
            buffer.delete(0, buffer.length());
            return res;
        }
        res = buffer.substring(0, pos);
        buffer.delete(0, pos + lineSeparator.length());
        return res;
    }

    public int nextInt(ScannerFilter filter) throws InputMismatchException, NoSuchElementException, IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        String num = next(filter);
        if (!checkInt(num)) {
            throw new InputMismatchException("next value is not int");
        }
        return parseInt(num);
    }

    public void close() {
        canRead = false;
        closed = true;
        try {
            input.close();
        } catch(IOException e) {
            inputException = e;
        }
    }
}

class DefaultFilter implements ScannerFilter {
    public boolean check(char value){
        return !Character.isWhitespace(value);
    }
}

interface ScannerFilter {
    boolean check(char value);
}