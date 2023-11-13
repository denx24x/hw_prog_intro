package util;
import java.util.Arrays;

public class IntList {
    private int[] arr;
    private int size;
    
    public IntList(){
        this.arr = new int[1];
        this.size = 0;
    }

    public int length(){
        return this.size;
    } 

    public void append(int value){
        if(this.size == this.arr.length){
            this.arr = Arrays.copyOf(this.arr, this.arr.length * 2);
        }
        this.arr[this.size] = value;
        this.size++;
    }

    public int get(int pos) throws IndexOutOfBoundsException {
        if(pos < 0 || pos >= this.size){
            throw new IndexOutOfBoundsException();
        }
        return this.arr[pos];
    }
}
