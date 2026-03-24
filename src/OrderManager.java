import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class OrderManager {

    private ArrayList<Order> orders = new ArrayList<>();

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");


    public void addOrder(Order order) {
        int nextOrderNumber = orders.size() + 1;
        order.setOrderNumber(nextOrderNumber);
        orders.add(order);
    }

    public void payOrder(int orderNumber) {
        for (Order current : orders) {
            if (current.getOrderNumber() == orderNumber) {
                current.setPaid();
                break;
            }
        }

    }


    public ArrayList<Order> activeOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isComplete() && !order.isCancelled()) {
                activeOrders.add(order);
            }
        }
        activeOrders.sort(Comparator.comparing(Order :: getEffectivePickUpTime));
        return activeOrders;
    }

    public ArrayList<Order> completedOrders() {
        ArrayList<Order> completedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isComplete() && !order.isCancelled()) {
                completedOrders.add(order);

            }
        }
        return completedOrders;
    }
    public void pizzaRanking(ArrayList<Product> menu){
        ArrayList<OrderLine> mostSellingProduct = new ArrayList<>();

        for(Product product : menu){
            OrderLine orderline = new OrderLine(0, product);
            mostSellingProduct.add(orderline);
        }
        for(Order order : completedOrders()){
            for(OrderLine orderline : order.getOrderLines()){
                for(OrderLine pizza : mostSellingProduct){
                    if(orderline.getPizza().equals(pizza.getPizza())) {
                        pizza.setQuantity(orderline.getQuantity() + pizza.getQuantity());
                        break;
                    }
                }
            }
        }
        mostSellingProduct.sort(Comparator.comparing(OrderLine :: getQuantity).reversed());
        for(OrderLine orderline : mostSellingProduct){
            System.out.println(orderline);
            System.out.println();
        }
    }
    public void printCompletedOrders(){
        for(Order order : completedOrders()){
            System.out.println(order);
        }
    }

    public ArrayList<Order> cancelledOrders() {
        ArrayList<Order> cancelledOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isCancelled()) {
                cancelledOrders.add(order);

            }
        }
        return cancelledOrders;
    }

    public String cancelledOrdersToString(){

        String s = "";
        ArrayList<Order> cancelledOrders = cancelledOrders();

        for (Order order : cancelledOrders){
            s+= order.getActiveOrder() + "\n" + "Total: " + order.getTotal() + "kr,-" + "\n\n" + "Bestilt: " +
                    order.getTimeOfOrder().format(formatter) + "\n" + "Afhentes: " +
                    order.getEffectivePickUpTime().format(formatter) + "\n\n";

        }
        return s;
    }


    public void removeIncompleteOrders() {
        orders.removeIf(order -> !order.isComplete());
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

    public double getOrderTotal(int orderNumber) {
        double total = 0;
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                total = order.getTotal();
                break;
            }
        }

        return total;
    }

    public Order getOrderByNumber(int number) {
        return orders.get(number);
    }

    public Order getActiveOrderByOrderNumber(int orderNumber) {
        for(Order order : activeOrders()){
            if(order.getOrderNumber() == orderNumber){
            return order;
            }
        }
        return null;
    }


}

