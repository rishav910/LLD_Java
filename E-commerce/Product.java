import java.util.Objects;

public class Product {
    private final String name;
    private final int price;

    public Product (String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName () {
        return name;
    }

    public int getPrice () {
        return price;
    }

    // Equal products - name and price should be same
    // equals() and hashCode() override
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        else if(!(obj instanceof Product)) return false; // Check if 'instanceof' Product

        // Type cast to product and check
        Product other = (Product) obj;
        return price == other.price && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        // Objects class method: hash(p1, p2, ...) -> Returns hash value of all parameters added
        return Objects.hash(name, price);
    }
}
