package expression;

import java.lang.StringBuilder;
import java.math.BigDecimal;

public class Divide extends PairExpression {
    public Divide(BaseExpression first, BaseExpression second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    protected String getSymbol() {
        return "/";
    }

    @Override
    public BigDecimal operation(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    @Override
    public int operation(int a, int b) {
        return a / b;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 853;
    }

    @Override
    public boolean associative() {
        return false;
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public int priorityRight() {
        return this.priority() - 1;
    }

}