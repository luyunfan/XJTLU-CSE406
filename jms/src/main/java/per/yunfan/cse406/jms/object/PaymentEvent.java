package per.yunfan.cse406.jms.object;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 一个代表支付事件的Java Bean
 */
public class PaymentEvent implements Serializable {

    /**
     * 事务ID
     */
    private String transId;

    /**
     * 支付名
     */
    private String payerName;

    /**
     * 金额
     */
    private BigDecimal amount;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentEvent(String transId, String payerName, BigDecimal amount) {
        this.transId = transId;
        this.payerName = payerName;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEvent that = (PaymentEvent) o;
        return Objects.equals(transId, that.transId) &&
                Objects.equals(payerName, that.payerName) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transId, payerName, amount);
    }

    @Override
    public String toString() {
        return "PaymentEvent{" +
                "transId='" + transId + '\'' +
                ", payerName='" + payerName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
