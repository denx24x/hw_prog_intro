package util;

import java.io.Reader;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


public class Scanner{
    private final Reader input;
    private StringBuilder buffer = new StringBuilder();
    private boolean canRead = true;
    private boolean closed = false;
    private String lineSeparator = System.lineSeparator();
    private final int BLOCK_SIZE = 1024;
    private IOException inputException = null;
    private int lastpos = -1;
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
            int pos = lastpos;
            for (int i = pos + 1; i <= buffer.length();i++) {
                if(!canRead && i == buffer.length() && i - pos > 1){
                    lastpos = pos;
                    return new IntPair(pos + 1, i);
                }
                if (i < buffer.length() && !filter.check(buffer.charAt(i))) {
                    if (i - pos != 1) {
                        lastpos = pos;
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
        lastpos = -1;
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
            lastpos = -1;
            buffer.delete(0, buffer.length());
            return res;
        }
        res = buffer.substring(0, pos + lineSeparator.length());
        lastpos = -1;
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