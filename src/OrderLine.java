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
                Pizza: %s
                Pris: %d kr.
                Stk: %d
                Total: %.2f kr.
                
                """, this.pizza.getName(), pizza.getPrice(),this.quantity, getTotal());
    }
}
