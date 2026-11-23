import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract sealed class MenuItem permits Beverage, Dish {
    private final String name;
    private BigDecimal price;

    protected MenuItem(String name, BigDecimal price) {
        this.name = requireName(name);
        this.price = requirePositivePrice(price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = requirePositivePrice(price);
    }

    public abstract String getCategory();

    public abstract String describe();

    private static String requireName(String name) {
        Objects.requireNonNull(name, "Menu item name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Menu item name must not be blank.");
        }
        return name.trim();
    }

    private static BigDecimal requirePositivePrice(BigDecimal price) {
        Objects.requireNonNull(price, "Menu item price must not be null.");
        BigDecimal rounded = price.setScale(2, RoundingMode.HALF_UP);
        if (rounded.signum() <= 0) {
            throw new IllegalArgumentException("Menu item price must be positive.");
        }
        return rounded;
    }

}
