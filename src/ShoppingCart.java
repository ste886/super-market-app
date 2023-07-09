import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> content;

    public ShoppingCart() {
        this.content = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        this.content.put(product, quantity);
    }

    @Override
    public String toString() {
        return this.content.toString();
    }
}
