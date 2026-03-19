public class Product {

    private final Pizza pizza;
    private int price;


    public Product(Pizza pizza, int price) {
        this.pizza = pizza;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPizza() {
        return pizza.getName();
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("""
                %s - %d,-%n""", pizza.getName(), getPrice());
    }
}
