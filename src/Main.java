import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Order order = new Order();
        Order order1 = new Order();
        OrderManager orderManager = new OrderManager();

        OrderLine orderline = new OrderLine(3, new Product(new Pizza
                ("hawai", "skinke"), 49));
        OrderLine orderline1 = new OrderLine(2, new Product(new Pizza
                ("amerikaner", "skinke, ost"), 49));
        OrderLine orderline2 = new OrderLine(2, new Product(new Pizza
                ("vesuvio", "peperoni, ost"), 49));
        OrderLine orderline3 = new OrderLine(8, new Product(new Pizza
                ("salat pizza", "salat, kebab"), 49));

        order.addOrderline(orderline);
        order.addOrderline(orderline1);

        order1.addOrderline(orderline2);
        order1.addOrderline(orderline3);

        order.setReady();
        order.setPaid();

        order1.setReady();
        order1.setPaid();

        orderManager.addOrder(order);
        orderManager.addOrder(order1);


        printActiveOrders(orderManager);
        printCompletedOrders(orderManager);
    }


    private static void printActiveOrders(OrderManager orderManager) {
//      Tjekker først og fremmest om activeOrders arraylisten er tom, hvis ja, returner ingenting/eksekver ikke metoden
        if (orderManager.activeOrders().isEmpty()) {
            return;
        }
//    Udprinter orderlines i activeOrders listen igennem og udprinter antallet af pizzaer, pizznavne, og bestlingstidpunkt
        for (Order order : orderManager.activeOrders()) {
            if (!order.isComplete()) {
                System.out.println(order.getOrderLinesInOrder() + "\n" +
                        "Bestilt kl: " + order.getTimeOfOrder() + "\n");
            }
        }

    }


    private static void printCompletedOrders(OrderManager orderManager) {

//        Tjekker først om der er nogen completed orders
        if (orderManager.completedOrders().isEmpty()) {
            return; // printer INTET
        }

        System.out.print("""
                        ORDER SUMMARY
                        -------------""");

//      For eachloop som itererer i completedOrders arraylisten og udprinter hver orderline i listen. quantity x pizza
        for (Order order : orderManager.completedOrders()) {
            System.out.printf("%n%s%n", order.getOrderLinesInOrder());
        }

//      Udskriver den totale pris for alle solgte pizzaer i completeOrders arraylisten.
        System.out.printf("""
                        Total amount: %.2f kr,-%nCompleted Orders (Total Pizzas Sold): %d
                        """,
                orderManager.getTotalIncome(), orderManager.getTotalPizzasSold());
    }

}












 /*
 noter
        Menu menu = buildMenu();
        private static Menu buildMenu() {
        Menu menu = new Menu();

        return menu;
    }

  */
