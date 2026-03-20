import java.util.ArrayList;

public class OrderManager {

    private ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> activeOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isComplete()) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }

    public ArrayList<Order> completedOrders() {
        ArrayList<Order> completedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isComplete()) {
                completedOrders.add(order);

            }
        }
        return completedOrders;
    }


    public void removeOrder(Order order) {
        orders.remove(order);
    }

    //forbundet med printCompletedOrders i Main. Indsamlet total pizzaquantity for alle ordrer og returnerer værdien
    public int getTotalPizzasSold() {
        int total = 0;
        for (Order order : orders) {
            if (order.isComplete()) {
                total += order.getQuantity();
            }
        }
        return total;
    }

    //forbundet med printCompletedOrders i Main.
    public double getTotalIncome() {
        double total = 0;
        for (Order order : orders) {
            if (order.isComplete()) {
                total += order.getTotal();
            }
        }
        return total;
    }



}
