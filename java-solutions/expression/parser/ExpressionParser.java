package expression.parser;

import expression.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayDeque;

public class ExpressionParser extends BaseParser implements Parser {
    @Override
    public TripleExpression parse(final String value) {
        System.err.println(value);
        setSource(new StringSource(value));
        return parseExpression("(");
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    private boolean isDigit() {
        return between('0', '9');
    }

    private void takeDigits(final StringBuilder sb) {
        while (isDigit()) {
            sb.append(take());
        }
    }

    private int takeInteger(final boolean minus) {
        final StringBuilder sb = new StringBuilder();
        if (minus || take('-')) {
            sb.append('-');
        }
        if (take('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            takeDigits(sb);
        } else {
            throw error("Invalid number");
        }
        
        return Integer.parseInt(sb.toString());
    }

    public BaseExpression parseValue() {
        final BaseExpression result;
        skipWhitespace();
        if (take('-')) {
            if (isDigit()) {
                result = new Const(takeInteger(true));
            } else {
                if (take('(')) {
                    result = new UnaryMinus(parseExpression("("));
                } else {
                    result = new UnaryMinus(parseValue());
                }
            }
        } else if (take('l')) {
            take();
            result = new HighterBits(parseValue());
        } else if (isDigit()) {
            result = new Const(takeInteger(false));
        } else if (take('t')) {
            take();
            result = new LowerBits(parseValue());
        } else if (take('(')) {
            result = parseExpression("(");
        } else if (take('x')) {
            result = new Variable("x");
        } else if (take('y')) {
            result = new Variable("y");
        } else if (take('z')) {
            result = new Variable("z");
        } else {
            throw error("Unexpected value: " + take());
        }
        return result;
    }

    public BaseExpression pm(BaseExpression left) {
        if (take('-')) {
            return new Subtract(left, parseValue());
        } else if (take('+')) {
            return new Add(left, parseValue());
        } else {
            return left;
        }
    }

    public BaseExpression md(BaseExpression left) {
        if (take('*')) {
            return new Multiply(left, parseValue());
        } else if (take('/')) {
            return new Divide(left, parseValue());
        } else {
            return left;
        }
    }

    public BaseExpression shift(BaseExpression left) {
        if (take('<')) {
            take();
            return new LeftShift(left, parseValue());
        } else if (take('>')) {
            take();
            if (take('>')) {
                return new ArithmeticRightShift(left, parseValue());
            } else {
                return new RightShift(left, parseValue());
            }
        } else {
            return left;
        }
    }

    public BaseExpression parseExpression(final String context) {
        BaseExpression value = parseValue();
        skipWhitespace();
        while(!eof() && !take(')')){
            skipWhitespace();
            value = pm(value);
            value = md(value);
            value = shift(value);
        }
        return value;
    } 
}