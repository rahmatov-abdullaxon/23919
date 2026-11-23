import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Objects;

public class CashPayment implements Payable {
    private final BigDecimal amountTendered;

    public CashPayment(BigDecimal amountTendered) {
        Objects.requireNonNull(amountTendered, "Amount tendered must not be null.");
        if (amountTendered.signum() <= 0) {
            throw new IllegalArgumentException("Amount tendered must be positive.");
        }
        this.amountTendered = amountTendered.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmountTendered() {
        return amountTendered;
    }

    @Override
    public String pay(BigDecimal amountDue) {
        Objects.requireNonNull(amountDue, "Amount due must not be null.");
        if (amountTendered.compareTo(amountDue) < 0) {
            throw new IllegalArgumentException("Not enough cash to cover the bill.");
        }
        BigDecimal change = amountTendered.subtract(amountDue);
        return String.format(Locale.US, "Paid in cash: $,.2f | Tendered: $,.2f | Change: $,.2f", amountDue,
                getAmountTendered(), change);
    }
}
