package expression;

import java.lang.StringBuilder;
import java.math.BigDecimal;

public class Subtract extends PairExpression {
    public Subtract(BaseExpression first, BaseExpression second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    public int operation(int a, int b) {
        return a - b;
    }

    @Override
    public BigDecimal operation(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() * 13;
    }

    @Override
    public boolean associative() {
        return false;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int priorityRight() {
        return this.priority();
    }

}