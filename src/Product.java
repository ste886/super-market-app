public class Product {
    private final String name;
    private final Double price;
    private final Integer barcode;

    public Product(String name, Double price, Integer barcode) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    // toString restituisce una stringa che descrive questo oggetto.
    @Override
    public String toString() {
        return "\t - " + name + " (prezzo: " + price + " Eur)";
    }

    public double getPrice() {
        return price;
    }
}
