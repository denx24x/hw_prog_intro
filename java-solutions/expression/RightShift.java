package expression;

import java.lang.StringBuilder;
import java.math.BigDecimal;

public class RightShift extends PairExpression {
    public RightShift(BaseExpression first, BaseExpression second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    protected String getSymbol() {
        return ">>";
    }

    @Override
    public int operation(int a, int b) {
        return a >> b;
    }

    @Override
    public BigDecimal operation(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 17;
    }

    @Override
    public boolean associative() {
        return true;
    }

    @Override
    public int priority() {
        return -10;
    }

    @Override
    public int priorityRight() {
        return this.priority() - 1;
    }

}