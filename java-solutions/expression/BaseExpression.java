package expression;

import java.math.BigDecimal;

public interface BaseExpression extends ToMiniString, Expression, TripleExpression, BigDecimalExpression {
    void join(StringBuilder dest);
    void joinMini(StringBuilder dest);
    int priority();
    int priorityRight();
    boolean associative();
}