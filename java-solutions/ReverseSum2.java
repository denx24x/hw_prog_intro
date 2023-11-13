import util.Scanner;
import util.DefaultFilter;

public class ReverseSum2 {
    public static int[] resize(int[] origin) {
        int[] newArray = new int[origin.length * 2];
        System.arraycopy(origin, 0, newArray, 0, origin.length);
        return newArray;
    }

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        int[] values = new int[1];
        int[] lineLength = new int[1];
        int elementsCount = 0;
        int lineNumber = 0;
        DefaultFilter filter = new DefaultFilter();
        while (mainScanner.hasNextLine()) {
            String line = mainScanner.nextLine();
            Scanner subScanner = new Scanner(line);
            if (lineLength.length == lineNumber) {
                lineLength = resize(lineLength);
            }
            while (subScanner.hasNextInt(filter)) {
                
                if (values.length == lineLength[lineNumber] + elementsCount) {
                    values = resize(values);
                }
                values[lineLength[lineNumber] + elementsCount] = subScanner.nextInt(filter);
                lineLength[lineNumber]++;
            }
            
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