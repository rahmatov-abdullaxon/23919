import java.math.BigDecimal;
import java.util.Locale;

public final class Beverage extends MenuItem {
    private final int volumeMl;

    public Beverage(String name, BigDecimal price, int volumeMl) {
        super(name, price);
        if (volumeMl <= 0) {
            throw new IllegalArgumentException("Beverage volume must be positive.");
        }
        this.volumeMl = volumeMl;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }

    @Override
    public String describe() {
        return String.format(Locale.US, "%s - %d ml", getName(), getVolumeMl());
    }
}
