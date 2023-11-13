package expression;

import java.lang.StringBuilder;
import java.math.BigDecimal;

public class Const implements BaseExpression {
    private BigDecimal value;

    public Const(int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public Const(BigDecimal value) {
        this.value = value;
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
        return String.valueOf(this.value);
    }

    @Override
    public int evaluate(int value) {
        return this.value.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal value) {
        return this.value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.value.intValue();
    }

    @Override 
    public String toMiniString() {
        return this.toString();
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        return this.value.equals(((Const)other).value);
    }   

    @Override
    public int hashCode() {
        return this.value.intValue() * 83;
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

}