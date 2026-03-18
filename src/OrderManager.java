import java.util.ArrayList;

public class OrderManager {

    private ArrayList<Order> orders = new ArrayList<>();

    // Tilføj ordre til aktive
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

