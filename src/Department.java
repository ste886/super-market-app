import java.util.Collection;
import java.util.Map;

public class Department {

    private final String name;
    private final Map<String, Product> products;

    public Department(String name, Map<String, Product> products) {
        this.name = name;
        this.products = products;
    }

    public boolean findProduct(String name) {
        if (products.containsKey(name)) {
            return true;
        } else {
            return false;
        }
    }

    public void printProducts() {
        System.out.println("Reparto: " + name);
        Collection<Product> values = products.values();  // ".values()" restituisce i valori della mappa
        for (Product value : values) {
            System.out.println(value);
        }
    }

    public String getName() {
        return name;
    }

    public Product getProductByName(String productName) {
        return products.get(productName);
    }
}

