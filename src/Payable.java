import java.math.BigDecimal;

public interface Payable {
    String pay(BigDecimal amountDue);
}
