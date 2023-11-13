package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Variable x = new Variable("x");
            System.out.println(new Add(new Subtract(new Multiply(x, x), new Multiply(new Const(2), x)), new Const(1)).evaluate(in.nextInt()));
        }catch(Exception e){
            System.out.println("Error!");
        }
    }
}