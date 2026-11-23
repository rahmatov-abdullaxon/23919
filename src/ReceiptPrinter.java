import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

public class ReceiptPrinter {
    private static final int WIDTH = 60;
    private static final String DOUBLE_LINE = "=".repeat(WIDTH);
    private static final String SINGLE_LINE = "-".repeat(WIDTH);

    private final PrintStream out;

    public ReceiptPrinter(PrintStream out) {
        this.out = Objects.requireNonNull(out, "Output stream must not be null.");
    }

    public void print(Order order) {
        Objects.requireNonNull(order, "Order must not be null.");
        printHeader(order);
        printLines(order);
        printTotals(order);
    }

    private void printHeader(Order order) {
        out.println(order.getRestaurant().getName());
        out.println(DOUBLE_LINE);
        out.println("Order No: " + order.getOrderId());
        out.println("Customer: " + order.getCustomer().getContactLine());
        out.println(SINGLE_LINE);
    }

    private void printLines(Order order) {
        for (OrderItem line : order.getItems()) {
            String label = line.getMenuItem().getName() + " x" + line.getQuantity();
            out.println(row(label, money(line.getLineTotal())));
        }
        out.println(SINGLE_LINE);
    }

    private void printTotals(Order order) {
        out.println(row("Subtotal:", money(order.getSubtotal())));
        String taxLabel = "Tax (" + percent(order.getRestaurant().getTaxRate()) + "%):";
        out.println(row(taxLabel, money(order.getTaxAmount())));
        out.println(row("TOTAL: ", money(order.getTotal())));
    }

    private static String row(String left, String right) {
        int gap = Math.max(1, WIDTH - left.length() - right.length());
        return left + " ".repeat(gap) + right;
    }

    private static String money(BigDecimal amount) {
        return String.format(Locale.US, "$%,.2f", amount);
    }

    private static String percent(BigDecimal rate) {
        return rate.movePointRight(2).stripTrailingZeros().toPlainString();
    }

}
