import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class CardPayment implements Payable {
    private static final Pattern CARD_PATTERN = Pattern.compile("^\\d{16}$");
    private static final int VISIBLE_DIGITS = 4;

    private final String cardNumber;

    public CardPayment(String cardNumber) {
        Objects.requireNonNull(cardNumber, "Card number must not be null.");
        String digits = cardNumber.replace(" ", "");
        if (!CARD_PATTERN.matcher(digits).matches()) {
            throw new IllegalArgumentException("Invalid card number.");
        }
        this.cardNumber = digits;
    }

    public String getMaskedCardNumber() {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - VISIBLE_DIGITS);
    }

    @Override
    public String pay(BigDecimal amountDue) {
        Objects.requireNonNull(amountDue, "Amount due must not be null.");
        return String.format(Locale.US, "Paid by card %s: $%,.2f | Status: approved", getMaskedCardNumber(),
                amountDue);
    }
}
