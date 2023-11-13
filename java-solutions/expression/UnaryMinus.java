package expression;

import java.math.BigDecimal;
import java.lang.StringBuilder;

public class UnaryMinus implements BaseExpression {
    private BaseExpression val;

    public UnaryMinus(BaseExpression val) {
        this.val = val;
    }
    
    @Override
    public void join(StringBuilder dest) {
        dest.append("-(");
        val.join(dest);
        dest.append(")");
    }

    @Override
    public void joinMini(StringBuilder dest) {
        if(val instanceof PairExpression){
            dest.append("-(");
            val.joinMini(dest);
            dest.append(")");
        }else{
            dest.append("- ");
            val.joinMini(dest);
        }
    }

    @Override
    public String toString() {
        StringBuilder dest = new StringBuilder();
        join(dest);
        return dest.toString();
    }

    @Override
    public int evaluate(int value) {
        return -val.evaluate(value);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return val.evaluate(x).negate();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -val.evaluate(x, y, z);
    }

    @Override 
    public String toMiniString() {
        StringBuilder dest = new StringBuilder();
        joinMini(dest);
        return dest.toString();
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(other.getClass() != this.getClass()) return false;
        return ((UnaryMinus)other).val.equals(this.val);
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
        return this.val.hashCode() * 353;
    }
}