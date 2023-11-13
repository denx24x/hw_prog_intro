import util.LineScanner;
import util.DefaultFilter;

public class ReverseHexAbc2 {
    public static int[] resize(int[] origin) {
        int[] newArray = new int[origin.length * 2];
        System.arraycopy(origin, 0, newArray, 0, origin.length);
        return newArray;
    }

    public static String toAbc(final int value) {
        final char[] chars = Integer.toString(value).toCharArray();
        for (int i = value < 0 ? 1 : 0; i < chars.length; i++) {
            chars[i] += 'a' - '0';
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        LineScanner mainScanner = new LineScanner(System.in);
        int[] values = new int[1];
        int[] lineLength = new int[1];
        int elementsCount = 0;
        int lineNumber = 0;
        DefaultFilter filter = new DefaultFilter();
        while (mainScanner.hasNextLine()) {
            if (lineLength.length == lineNumber) {
                lineLength = resize(lineLength);
            }
            while (mainScanner.hasNextInt(filter)) {
                
                if (values.length == lineLength[lineNumber] + elementsCount) {
                    values = resize(values);
                }
                values[lineLength[lineNumber] + elementsCount] = mainScanner.nextInt(filter);
                lineLength[lineNumber]++;
            }
            mainScanner.switchLine();
            elementsCount += lineLength[lineNumber];
            lineNumber++;
        }
        for (int line = lineNumber - 1; line >= 0; line--) {
            elementsCount -= lineLength[line];

            for (int ind = lineLength[line] - 1; ind >= 0; ind--) {
                System.out.print(toAbc(values[elementsCount + ind]));
                if (ind != 0) {
                    System.out.print(" ");
                }
            }
            
            System.out.println();
        }
    }
}