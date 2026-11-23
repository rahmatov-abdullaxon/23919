import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    public static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{12}$");

    private final String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        Objects.requireNonNull(name, "Customer name must not be null.");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Customer name must not be blank.");
        }
        this.name = name;
        this.phoneNumber = requireValidPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = requireValidPhoneNumber(phoneNumber);
    }

    public String getContactLine() {
        return getName() + " (" + getPhoneNumber() + ")";
    }

    private String requireValidPhoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "Customer phone number must not be null.");
        if (phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Customer phone number must not be blank.");
        }

        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Customer phone number must be exactly 12 digits.");
        }
        return phoneNumber;
    }
}
