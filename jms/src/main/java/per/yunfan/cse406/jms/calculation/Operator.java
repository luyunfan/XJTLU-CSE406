package per.yunfan.cse406.jms.calculation;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("×"),
    DIVIDE("÷");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
