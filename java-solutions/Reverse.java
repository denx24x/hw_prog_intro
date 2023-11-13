import util.LineScanner;
import util.DefaultFilter;

public class Reverse {
    public static int[] resize(int[] origin) {
        int[] newArray = new int[origin.length * 2];
        System.arraycopy(origin, 0, newArray, 0, origin.length);
        return newArray;
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
                System.out.print(values[elementsCount + ind]);
                if (ind != 0) {
                    System.out.print(" ");
                }
            }
            
            System.out.println();
        }
    }
}