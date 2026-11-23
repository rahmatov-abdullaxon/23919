import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Restaurant {
    private final String name;
    private final BigDecimal taxRate;
    private final List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name, BigDecimal taxRate) {
        Objects.requireNonNull(name, "Restaurant name must not be null.");
        Objects.requireNonNull(taxRate, "Tax rate must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Restaurant name must not be blank.");
        }
        if (taxRate.signum() < 0 || taxRate.compareTo(BigDecimal.ONE) >= 0) {
            throw new IllegalArgumentException("Tax rate must be at least 0 and below 1 (for example 0.10).");
        }

        this.name = name.trim();
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    public void addToMenu(MenuItem Item) {
        Objects.requireNonNull(Item, "Menu item must not be null.");
        if (findMenuItem(Item.getName()).isPresent()) {
            throw new IllegalArgumentException("Already on the menu: " + Item.getName());
        }
        menu.add(Item);
    }

    public boolean offers(MenuItem Item) {
        return menu.contains(Item);
    }

    public MenuItem getMenuItem(String itemName) {
        return findMenuItem(itemName)
                .orElseThrow(() -> new IllegalArgumentException("Not on the menu: " + itemName));
    }

    private Optional<MenuItem> findMenuItem(String itemName) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}
