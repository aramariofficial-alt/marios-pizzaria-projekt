import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private ArrayList<OrderLine> list = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final LocalTime timeOfOrder = LocalTime.now();
    private LocalTime pickupTime = timeOfOrder.plusMinutes(15);

    boolean isReady;
    boolean isPaid;

    public void addOrderline(OrderLine orderLine) {
        list.add(orderLine);
    }

    public LocalTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setPickupTime(int chosenHour, int chosenMinutes) {
        this.pickupTime = LocalTime.of(chosenHour, chosenMinutes);
    }

    public LocalTime getPickupTime() {
        return pickupTime;
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

    public String getPizza() {
        String pizza = "";
        for (OrderLine orderline : list) {
            return orderline.getPizza();
        }
        return pizza;
    }

    public int getQuantity() {
        int quantity = 0;
        for (OrderLine orderline : list) {
            quantity += orderline.getQuantity();
        }
        return quantity;
    }

    public String getOrderLinesInOrder(){
        String h = "";
        for (OrderLine line : list){
            h += line.getQuantity() + " x " + line.getPizza() + "\n";
        }

        return h;
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
                        Bestilt kl: %s%n
                        Afhentningstidspunkt kl: %s""",
                sb.toString(),
                getTotal(),
                timeOfOrder.format(formatter), pickupTime.format(formatter)
        );
    }
}

