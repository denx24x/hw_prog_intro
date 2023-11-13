package expression;

import java.math.BigDecimal;
import java.lang.StringBuilder;

public class HighterBits implements BaseExpression {
    private BaseExpression val;

    public HighterBits(BaseExpression val) {
        this.val = val;
    }
    
    @Override
    public void join(StringBuilder dest) {
        dest.append("l0(");
        val.join(dest);
        dest.append(")");
    }

    @Override
    public void joinMini(StringBuilder dest) {
        if(val instanceof PairExpression){
            dest.append("l0(");
            val.joinMini(dest);
            dest.append(")");
        }else{
            dest.append("l0 ");
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
        int v = val.evaluate(value);
        return count(v);
    }

    private int count(int v){
        int ans = 0;
        for(int i = 31; i >= 0; i--){
            if((v & (1 << i)) != 0){
                return ans;
            }
            ans += 1;
        }
        return ans;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return val.evaluate(x).negate();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int v = val.evaluate(x, y, z);
        return count(v);
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
        return ((HighterBits)other).val.equals(this.val);
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
        return this.val.hashCode() * 751;
    }
}