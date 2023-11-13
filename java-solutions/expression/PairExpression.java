package expression;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class PairExpression implements BaseExpression {
    protected BaseExpression first, second;

    abstract protected int operation(int a, int b);

    abstract protected BigDecimal operation(BigDecimal a, BigDecimal b);

    abstract protected String getSymbol();

    @Override
    public void join(StringBuilder dest) {
        this.first.join(dest.append('('));
        dest.append(" ").append(this.getSymbol()).append(" ");
        this.second.join(dest);
        dest.append(')');
    }

    @Override
    public int evaluate(int v) {
        return operation(first.evaluate(v), second.evaluate(v));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return operation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        join(answer);
        return answer.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder ans = new StringBuilder();
        this.joinMini(ans);
        return ans.toString();
    }

    public void joinMini(StringBuilder dest) {
        int firstPriority = first.priority();
        int secondPriority = second.priorityRight();
        if (this.priority() <= firstPriority) {
            this.first.joinMini(dest);
        } else {
            dest.append("(");
            this.first.joinMini(dest);
            dest.append(")");
        }
        dest.append(" ").append(this.getSymbol()).append(" ");
        if (this.priority() < secondPriority || this.priority() == secondPriority && this.associative()) {
            this.second.joinMini(dest);
        } else {
            dest.append("(");
            this.second.joinMini(dest);
            dest.append(")");
        }
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != this.getClass()) {
            return false;
        }
        PairExpression otherConverted = (PairExpression) other;
        return otherConverted.first.equals(this.first) && otherConverted.second.equals(this.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}