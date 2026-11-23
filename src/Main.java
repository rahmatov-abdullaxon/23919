import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = createRestaurant();
        ReceiptPrinter printer = new ReceiptPrinter(System.out);
        showMenu(restaurant);
        System.out.println();
        serveCustomer(restaurant, printer);
    }

    private static Restaurant createRestaurant() {
        Restaurant restaurant = new Restaurant("Chorsu Grill House", new BigDecimal("0.10"));
        restaurant.addToMenu(new Dish("Pizza", new BigDecimal("8.50"), 500));
        restaurant.addToMenu(new Dish("Lamb Kebab", new BigDecimal("11.00"), 700));
        restaurant.addToMenu(new Dish("Plov", new BigDecimal("7.00"), 1000));
        restaurant.addToMenu(new Beverage("Iced Tea", new BigDecimal("3.30"), 400));
        restaurant.addToMenu(new Beverage("Green Tea", new BigDecimal("2.50"), 300));

        return restaurant;
    }

    private static void showMenu(Restaurant restaurant) {
        System.out.println("MENU");
        System.out.println("=".repeat(40));
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(item.getCategory() + ": " + item.describe());
        }
    }

    private static void serveCustomer(Restaurant restaurant, ReceiptPrinter printer) {
        Customer customer = new Customer("Aziz Karimov", "998901234567");
        Order order = new Order(1, restaurant, customer);

        order.addItem(new OrderItem(restaurant.getMenuItem("Pizza"), 1));
        order.addItem(restaurant.getMenuItem("Lamb Kebab"), 2);
        order.addItem(restaurant.getMenuItem("Iced Tea"), 2);

        printer.print(order);

        Payable payment = new CashPayment(new BigDecimal("50.00"));
        System.out.println(payment.pay(order.getTotal()));
    }
}
