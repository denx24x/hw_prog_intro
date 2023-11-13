import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import util.Scanner;
import util.WordFilter;

public class WordStatInput {

    public static void main(String[] args){
        StringIntPair[] values = new StringIntPair[1];
        int ln = 0;        
        try {
            FileReader file = new FileReader(args[0], StandardCharsets.UTF_8);
            Scanner in = new Scanner(file);
            WordFilter filter = new WordFilter();
            while (in.hasNext(filter)) {
                if (ln == values.length) {
                    values = Arrays.copyOf(values, values.length * 2);
                }
                String v = in.next(filter);
                values[ln] = new StringIntPair(v.toLowerCase(), ln);
                ln++;
            }
            in.close();
            if(in.hasException()){
                System.err.println("Can`t open input file: " + in.getException());
                return;
            }    
        } catch(IOException e) {
            System.err.println("Can`t open input file: " + e.getMessage());
            return;
        }
        
        
        Arrays.sort(values, 0, ln, (a, b) -> a.first.compareTo(b.first) * 2 + Integer.signum(a.second - b.second));
        int pos = 0;
        for (int i = 1; i <= ln;i++) {
            if (i != ln && values[i].first.equals(values[i - 1].first)){
                values[i].second = values[i - 1].second;
            } else {
                values[i - 1].cnt = i - pos;
                pos = i;
            }
        }
        Arrays.sort(values, 0, ln, (a, b) -> Integer.signum(a.second - b.second));

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
            try {
                for (int i = 1; i <= ln;i++) {
                    if (values[i - 1].cnt == 0) {
                        continue;
                    }
                    if (i == ln || values[i].second != values[i - 1].second) {  
                        out.write(values[i - 1].first + " " + values[i - 1].cnt);
                        out.newLine();
                    }
                }
            } finally {
                out.close();
            }
        } catch(FileNotFoundException e) {
            System.err.println("Can`t open output file: " + e.getMessage());
        } catch(IOException e) {
            System.err.println("Error when writing to output file: " + e.getMessage());
        }
    }
}
