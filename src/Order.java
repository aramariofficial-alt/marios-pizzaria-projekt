import java.util.ArrayList;

public class Order {
    ArrayList<OrderLine> list = new ArrayList<>();
    boolean isReady;
    boolean isPaid;
    boolean isComplete;

    public void addOrderline(OrderLine orderLine){
        list.add(orderLine);
    }

    public double getTotal(){
        double total = 0;
        for (OrderLine line : list){
            total += line.getTotal();
        }
        return total;
    }


    public String toString(){
        return String.format("""
                %s
                ----
                TOTAL: %.2f""",list,getTotal());
    }

}
