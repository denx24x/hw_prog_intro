package util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


public class LineScanner{
    private final BufferedReader input;
    private StringBuilder buffer = new StringBuilder();
    private boolean canRead = true;
    private boolean closed = false;
    private String lineSeparator = System.lineSeparator();
    private IOException inputException = null;
    private int start = -1;

    public LineScanner(Reader in) {
        input = new BufferedReader(in);
        switchLine();
    }

    public LineScanner(String in) {
        input = new BufferedReader(new StringReader(in));
        switchLine();
    }

    public LineScanner(InputStream in) {
        input = new BufferedReader(new InputStreamReader(in));
        switchLine();
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
        buffer.delete(0, buffer.length());
        if (!canRead) return;
        try {
            String val = input.readLine();
            if(val == null){
                canRead = false;
                return;
            }
            buffer.append(val);
        } catch(IOException ex) {
            canRead = false;
            inputException = ex;
        }    
    }

    public boolean hasNext(ScannerFilter filter) throws IllegalStateException {
        if(closed) throw new IllegalStateException("Scanner is closed");
        return getNext(filter).first != -1;
    }

    public void switchLine(){
        start = -1;
        read();
    }

    private IntPair getNext(ScannerFilter filter) {
        int pos = start;
        for (int i = pos + 1; i <= buffer.length();i++) {
            if(i == buffer.length() && i - pos > 1){
                return new IntPair(pos + 1, i);
            }
            if (i < buffer.length() && !filter.check(buffer.charAt(i))) {
                if (i - pos != 1) {
                    return new IntPair(pos + 1, i);
                }
                pos = i;
            }
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
        start = pos.second;
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
        return canRead;
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