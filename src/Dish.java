import java.math.BigDecimal;
import java.util.Locale;

public final class Dish extends MenuItem {
    private final int calories;

    public Dish(String name, BigDecimal price, int calories) {
        super(name, price);
        if (calories <= 0) {
            throw new IllegalArgumentException("calories must be positive.");
        }
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String getCategory() {
        return "Dish";
    }

    @Override
    public String describe() {
        return String.format(Locale.US, "%s - %d kcal", getName(), getCalories());
    }
}
