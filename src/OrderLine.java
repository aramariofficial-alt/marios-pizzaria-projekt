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
        return String.format("""
                %d X %s %.2f kr.
                """, this.quantity, this.pizza.getName(), getTotal());
    }
}
