package expression;

import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.lang.ArithmeticException;

public class LeftShift extends PairExpression {
    public LeftShift(BaseExpression first, BaseExpression second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    protected String getSymbol() {
        return "<<";
    }

    @Override
    public int operation(int a, int b) {
        return a << b;
    }

    @Override
    public BigDecimal operation(BigDecimal a, BigDecimal b) {
        throw new ArithmeticException("Operation is not supported!");
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 449;
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