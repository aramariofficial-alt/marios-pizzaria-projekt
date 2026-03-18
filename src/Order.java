import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    ArrayList<OrderLine> list = new ArrayList<>();
    LocalTime bestillingstidspunkt = LocalTime.now();
    boolean isReady;
    boolean isPaid;
    //boolean isComplete; //når pizzaen isReady && isPaid
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public Order() {
        bestillingstidspunkt = LocalTime.now();
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
                Bestilt kl: %s""",list,getTotal(), bestillingstidspunkt.format(formatter));

    }

}
