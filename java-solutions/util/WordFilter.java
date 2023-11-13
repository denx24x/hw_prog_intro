package util;

public class WordFilter implements ScannerFilter {
    public boolean check(char val){
        return Character.isLetter(val) || val == '\'' || Character.getType(val) == Character.DASH_PUNCTUATION;
    }
}
