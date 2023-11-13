import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.util.LinkedHashMap;
import java.util.Map;
import util.*;

public class Wspp{
    public static void main(String[] args){
        LinkedHashMap<String, IntList> array = new LinkedHashMap<String, IntList>();
                int ln = 0;        
        try {
            FileReader file = new FileReader(args[0], StandardCharsets.UTF_8);
            Scanner in = new Scanner(file);
            WordFilter filter = new WordFilter();
            try {
                while(in.hasNext(filter)){
                    String v = in.next(filter).toLowerCase();
                    if(!array.containsKey(v)){
                        array.put(v, new IntList());
                    }
                    array.get(v).append(ln + 1);
                    ln++;
                }
            } finally {
                in.close();
            }
        } catch(FileNotFoundException e) {
            System.err.println("Can`t open input file: " + e.getMessage());
            return;
        } catch(IOException e) {
            System.err.println("Error when reading input file: " + e.getMessage());
            return;
        }
        

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, IntList> entry : array.entrySet()) {
                    IntList value = entry.getValue();
                    out.write(entry.getKey() + " " +  value.length() + " ");
                    for(int i = 0; i < value.length();i++){
                        out.write(value.get(i) + (i == value.length() - 1 ? "" : " "));
                    }
                    out.newLine();
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