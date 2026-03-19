import java.util.ArrayList;

public class OrderManager {

    private ArrayList<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
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

    public ArrayList<Order> activeOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isComplete()) {
                activeOrders.add(order);
            }
        }

        return activeOrders;
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


//    Flyt færdige ordrer til summary
//    public void updateOrders() {
//
//    }

//     Overblik over alle aktive ordrer
//     Skal indeholde quantiy, getPizzaName, getTimeofOrder. Eksempel: 3 x Hawai     Bestilt kl: 20:30
//    public String viewActiveOrders() {
//
//    }

//      Overblik over alle pizzaer som ikke er active
//     Skal indeholde overskrift "ORDER SUMMARY", Completed orders     total:
//     3 x pizzaName
//     2 x pizzaName
//     og til sidst total amount: (samlet indtægt fra alle ordrer)
//    public String viewOrderSummary() {
//    }

