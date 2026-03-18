import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Order {
    ArrayList<OrderLine> list = new ArrayList<>();
    LocalTime timeOfOrder = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    boolean isReady;
    boolean isPaid;

    public void addOrderline(OrderLine orderLine) {
        list.add(orderLine);
    }

    public LocalTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public double getTotal() {
        double total = 0;
        for (OrderLine line : list) {
            total += line.getTotal();
        }
        return total;
    }

    public void setReady() {  // isReady sættes til true
        isReady = true;
    }

    public void setPaid() { // isPaid sættes til true
        isPaid = true;
    }

    public boolean isComplete() {
        return isPaid && isReady;
    }


    @Override  //toString metode som fjerner "[ , ]" fra konsollen når ordren udprintes. chat har lavet den
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (OrderLine line : list) {
            sb.append(line).append("\n");
        }

        return String.format("""
                        %s
                        ----
                        TOTAL: %.2f
                        Bestilt kl: %s""",
                sb.toString(),
                getTotal(),
                timeOfOrder.format(formatter)
        );
    }
}

//    vores forrige toString
//    public String toString(){  //
//        return String.format("""
//                %s
//                ----
//                TOTAL: %.2f
//                Bestilt kl: %s""",list,getTotal(), timeOfOrder );
//
//    }


