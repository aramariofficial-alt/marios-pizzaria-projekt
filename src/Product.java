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
//    Bør vi have en toString i klassen?⬇️
//    @Override
//    public String toString() {
//        return String.format();
//    }
}
