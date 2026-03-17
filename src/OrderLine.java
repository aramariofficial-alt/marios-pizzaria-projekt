public class OrderLine {
    private int quantity;
    private Pizza pizza;

    public OrderLine(int quantity, Pizza pizza){
        this.quantity = quantity;
        this.pizza = pizza;
    }
    public double getTotal(){
        return pizza.getPrice() * quantity;
    }
    public String toString(){
        return String.format("%s %d %d %2f", this.pizza.getName(), this.quantity, pizza.getPrice(), getTotal());
    }
}
