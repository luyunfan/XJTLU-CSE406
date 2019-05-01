package per.yunfan.cse406.jms.object;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PaymentEvent implements Serializable {
    private String transId;

    private String payerName;

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
