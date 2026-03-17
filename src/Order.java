import java.time.LocalTime;
import java.util.ArrayList;

public class Order {
    ArrayList<OrderLine> list = new ArrayList<>();
    LocalTime timeOfOrder = LocalTime.now();
    boolean isReady;
    boolean isPaid;

    public Order() {
        timeOfOrder = LocalTime.now();
    }

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

    public void setReady() {
        isReady = true;
    }

    public void setPaid() {
        isPaid = true;
    }

    public boolean isComplete() {
        return isPaid && isReady;
    }



    public String toString(){
        return String.format("""
                %s
                ----
                TOTAL: %.2f
                Bestilt kl: %s""",list,getTotal(), timeOfOrder );

    }

}
