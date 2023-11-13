import util.ScannerFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import util.*;
import java.util.TreeMap;

class WordList extends IntList{
    private int lastPos, lastLine, total;

    public WordList(){
        super();
        this.lastPos = 1;
        this.lastLine = 0;
        this.total = 0;
    }   

    public int getTotal(){
        return this.total;
    }

    public void append(int value, int line){
        if(this.lastLine != line){
            this.lastPos = 1;
        }
        if(this.lastPos % 2 == 0){ 
            super.append(value);
        }
        this.lastPos++;
        this.lastLine = line;
        this.total++;
    }
}

public class WsppSortedSecondG {
    public static void main(String[] args){
        Map<String, WordList> array = new TreeMap<>();     
        try (FileReader file = new FileReader(args[0], StandardCharsets.UTF_8)){
            Scanner in = new Scanner(file);
            WordFilter filter = new WordFilter();
            int last = 0;
            int line = 0;
            while (in.hasNext(filter)) {
                if (in.isNextOnNewLine(filter)) {
                    line++;
                }
                String v = in.next(filter).toLowerCase();
                if (!array.containsKey(v)) {
                    array.put(v, new WordList());
                }
                array.get(v).append(last + 1, line);
                last++;
            }
            if (in.hasException()) {
                throw in.getException();
            }
        } catch(FileNotFoundException e) {
            System.err.println("Can`t open input file: " + e.getMessage());
            return;
        } catch(IOException e) {
            System.err.println("Error when reading input file: " + e.getMessage());
            return;
        }
        try(BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))){
            for (Map.Entry<String, WordList> entry : array.entrySet()) {
                WordList value = entry.getValue();
                out.write(entry.getKey() + " " +  value.getTotal());
                for (int i = 0; i < value.length();i++) {
                    out.write(" " + value.get(i));
                }
                out.newLine();
            }
        } catch(FileNotFoundException e) {
            System.err.println("Can`t open output file: " + e.getMessage());
        } catch(IOException e) {
            System.err.println("Error when writing to output file: " + e.getMessage());
        }
    }
}