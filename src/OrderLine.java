public class OrderLine {
    private int quantity;
    private final Product product;

    public OrderLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getPizza() {
        return product;
    }

    public String toString() {
        return String.format("""
                %d X %s: %.2f kr.""", this.quantity, product.getPizza(), getTotal());
    }
}


