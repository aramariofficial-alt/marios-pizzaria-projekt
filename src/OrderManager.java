import java.util.ArrayList;

public class OrderManager {

    private ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        int nextOrderNumber = orders.size() + 1;
        order.setOrderNumber(nextOrderNumber);
        orders.add(order);
    }
    public void payOrder(int orderNumber){
        for (Order current : orders){
            if (current.getOrderNumber() == orderNumber){
                current.setPaid();
                break;
            }else {
                //it was not the right order, so we continue.
            }
        }


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
            if (order.isComplete())
                total += order.getTotal();
            }
        return total;
    }

    public double getOrderTotal(int orderNumber){
        double total = 0;
        for (Order order : orders){
            if (order.getOrderNumber() == orderNumber){
                total = order.getTotal();
                break;
            }
        }

         return total;
    }
}
