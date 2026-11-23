import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Collections;

public class Order {
    private final int orderNumber;
    private final Restaurant restaurant;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(int orderNumber, Restaurant restaurant, Customer customer) {
        if (orderNumber <= 0) {
            throw new IllegalArgumentException("Order number must be positive.");
        }

        this.orderNumber = orderNumber;
        this.restaurant = Objects.requireNonNull(restaurant, "Restaurant must not be null.");
        this.customer = Objects.requireNonNull(customer, "Customer must not be null");
    }

    public String getOrderId() {
        return String.format(Locale.US, "ORD-%04d", orderNumber);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem line) {
        Objects.requireNonNull("Order line must not be null.");
        if (!restaurant.offers(line.getMenuItem())) {
            throw new IllegalArgumentException("Not on this restaurant's menu: " + line.getMenuItem().getName());
        }
        items.add(line);
    }

    public void addItem(MenuItem menuItem, int quantity) {
        addItem(new OrderItem(menuItem, quantity));
    }

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxAmount() {
        return getSubtotal().multiply(restaurant.getTaxRate()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTaxAmount());
    }
}
