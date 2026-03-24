import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private final ArrayList<OrderLine> list = new ArrayList<>();
    private final LocalDateTime timeOfOrder = LocalDateTime.now();
    private LocalDateTime pickUpTime;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");


    private boolean isReady;
    private boolean isPaid;
    private boolean isCancelled;
    private int orderNumber;

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled() {
        isCancelled = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isReady() {
        return isReady;
    }

    public void addOrderline(OrderLine orderLine) {
        list.add(orderLine);
    }
    public ArrayList<OrderLine> getOrderLines(){
        return list;
    }

    public LocalDateTime addPickUpTime(int hour, int minute) {
        this.pickUpTime = timeOfOrder.withHour(hour).withMinute(minute);
        return pickUpTime;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public LocalDateTime getEffectivePickUpTime() {
        if (pickUpTime == null) {
            return timeOfOrder.plusMinutes(15);
        }
        return pickUpTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumberString() {
        return Integer.toString(this.orderNumber);
    }

    public double getTotal() {
        double total = 0;
        for (OrderLine line : list) {
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

    public String getPizza() {
        String result = "";
        for (OrderLine orderline : list) {
            result += orderline.getPizza() + ", ";
        }
        return result;
    }

    public int getQuantity() {
        int quantity = 0;
        for (OrderLine orderline : list) {
            quantity += orderline.getQuantity();
        }
        return quantity;
    }

    public String getOrderLinesInOrder() {
        String h = "";
        for (OrderLine line : list) {
            h += line.getQuantity() + " x " + line.getPizza() + "\n";
        }
        return h;
    }

    public String getActiveOrder() {
        String h = "";
        for (OrderLine line : list) {
            h += line.getQuantity() + " x " + line.getPizza() + "\n";
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (OrderLine line : list) {
            sb.append(line).append("\n");
        }

        return String.format("""
                        Ordrenummer: %s
                        %s
                        -----
                        TOTAL: %.2f kr.
                        Bestilt: %s
                        Afhentes: %s
                        -------------""", getOrderNumber(),
                sb.toString(),
                getTotal(),
                timeOfOrder.format(formatter),
                getEffectivePickUpTime().format(formatter)
        );
    }
}