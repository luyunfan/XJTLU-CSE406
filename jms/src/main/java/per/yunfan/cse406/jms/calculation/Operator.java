package per.yunfan.cse406.jms.calculation;

/**
 * 封装了加、减、乘、除四则元素符的枚举
 */
public enum Operator {

    /**
     * 加
     */
    ADD("+"),

    /**
     * 减
     */
    SUBTRACT("-"),

    /**
     * 乘
     */
    MULTIPLY("×"),

    /**
     * 除
     */
    DIVIDE("÷");

    /**
     * toString时的符号表达式
     */
    private final String operatorString;

    /**
     * 构造方法，传入代表符号的字符串
     *
     * @param operatorString 符号对应的字符串
     */
    Operator(String operatorString) {
        this.operatorString = operatorString;
    }

    /**
     * @return 符号对应的字符串
     */
    @Override
    public String toString() {
        return this.operatorString;
    }
}
