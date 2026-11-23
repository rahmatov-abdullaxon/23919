import java.util.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItem {
    private static final int MAX_QUANTITY = 25;

    private final MenuItem menuItem;
    private final BigDecimal unitPrice;
    private final int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        Objects.requireNonNull(menuItem, "Menu item must not be null.");
        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Quantity must be between 1 and " + MAX_QUANTITY + ".");
        }
        this.menuItem = menuItem;
        this.unitPrice = menuItem.getPrice();
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

}
