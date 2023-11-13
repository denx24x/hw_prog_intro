package util;

public class DefaultFilter implements ScannerFilter {
    public boolean check(char value){
        return !Character.isWhitespace(value);
    }
}
