package expression;

import java.math.BigDecimal;
import java.lang.StringBuilder;

public class Variable implements BaseExpression {
    private String key;

    public Variable(String key) {
        this.key = key;
    }
    
    @Override
    public void join(StringBuilder dest) {
        dest.append(this.toString());
    }

    @Override
    public void joinMini(StringBuilder dest) {
        join(dest);
    }

    @Override
    public String toString() {
        return this.key;
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return  x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(key.equals("x")) return x;
        if(key.equals("y")) return y;
        return z;
    }

    @Override 
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        return this.key.equals(((Variable)other).key);
    } 

    @Override
    public boolean associative() {
        return true;
    }

    @Override
    public int priority() {
        return 255;
    }

    @Override
    public int priorityRight() {
        return this.priority();
    }

    @Override
    public int hashCode() {
        return this.key.hashCode() * 61;
    }
}