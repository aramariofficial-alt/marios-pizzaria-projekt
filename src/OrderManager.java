import java.util.ArrayList;
import java.util.Iterator;

public class OrderManager {

    private ArrayList<Order> activeOrders = new ArrayList<>();
    private ArrayList<Order> orderSummary = new ArrayList<>();

    // Tilføj ordre til aktive
    public void addOrder(Order order) {
        activeOrders.add(order);
    }

//    Flyt færdige ordrer til summary
//    public void updateOrders() {
//
//    }

//     Overblik over alle aktive ordrer
//     Skal indeholde quantiy, getPizzaName, getTimeofOrder. Eksempel: 3 x Hawai     Bestilt kl: 20:30
//    public String getKitchenView() {
//
//    }

//      Overblik over alle pizzaer som ikke er active
//     Skal indeholde overskrift "ORDER SUMMARY", Completed orders     total:
//     3 x pizzaName
//     2 x pizzaName
//     og til sidst total amount: (samlet indtægt fra alle ordrer)
//    public String getSummaryView() {
//    }

}