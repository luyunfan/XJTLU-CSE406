package per.yunfan.cse406.jms.calculation;

import java.io.Serializable;
import java.util.Objects;

public class Expression<T extends Number> implements Serializable {
    private final T first;
    private final T second;
    private final Operator operator;

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression(T first, T second, Operator operator) {
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression<?> that = (Expression<?>) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, operator);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", first, operator, second);
    }
}
