import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        OrderManager orderManager = new OrderManager();
        Scanner scanner = new Scanner(System.in);

//        buildMenu();
//        printMenu();

        Order order = new Order();
        order.addOrderline(new OrderLine(3, new Product(new Pizza("hawai", "skinke"), 49)));
        order.addOrderline(new OrderLine(1, new Product(new Pizza("vesuvio", "ost"), 49)));
        order.setPaid();
        order.addPickUpTime(18, 30);

        // Order 2 (har pickup)
        Order order1 = new Order();
        order1.addOrderline(new OrderLine(2, new Product(new Pizza("amerikaner", "ost"), 49)));
        order1.addOrderline(new OrderLine(2, new Product(new Pizza("pepperoni", "pepperoni"), 49)));
        order1.setPaid();
        order1.addPickUpTime(18, 15);

        // Order 3 (INGEN pickup → +20 min)
        Order order2 = new Order();
        order2.addOrderline(new OrderLine(1, new Product(new Pizza("vesuvio", "ost"), 49)));
        order2.addOrderline(new OrderLine(3, new Product(new Pizza("salat pizza", "kebab"), 49)));
        order2.setPaid();

        // Order 4 (INGEN pickup → +20 min)
        Order order3 = new Order();
        order3.addOrderline(new OrderLine(4, new Product(new Pizza("pepperoni", "pepperoni"), 49)));
        order3.addOrderline(new OrderLine(1, new Product(new Pizza("hawai", "skinke"), 49)));
        order3.setPaid();

        // Order 5 (har pickup)
        Order order4 = new Order();
        order4.addOrderline(new OrderLine(2, new Product(new Pizza("salat pizza", "kebab"), 49)));
        order4.addOrderline(new OrderLine(2, new Product(new Pizza("amerikaner", "ost"), 49)));
        order4.setPaid();
        order4.addPickUpTime(20,15);

        // Tilføj alle ordrer
        orderManager.addOrder(order);
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);
        orderManager.addOrder(order3);
        orderManager.addOrder(order4);

       // orderManager.removeOrder(order);
        printActiveOrders(orderManager);


    }

    private static void printActiveOrders(OrderManager orderManager) {

        if (orderManager.activeOrders().isEmpty()) {
            System.out.println("""
                    ACTIVE ORDERS: 0
                    -------------""");
            return;
        }

        System.out.println("ACTIVE ORDERS:\n------------");

        ArrayList<Order> sortedOrders = new ArrayList<>(orderManager.activeOrders());

        sortedOrders.sort((o1, o2) ->
                o1.getEffectivePickUpTime().compareTo(o2.getEffectivePickUpTime())
        );

        for (Order order : sortedOrders) {
            if (!order.isComplete()) {

                System.out.println("\n" + order.getOrderLinesInOrder()
                        + "\nTime of order: " + order.getTimeOfOrder()
                        + "\nPick up time: " + order.getEffectivePickUpTime());
            }
        }
    }

    private static void printCompletedOrders(OrderManager orderManager) {

        if (orderManager.completedOrders().isEmpty()) {
            return;
        }

        System.out.print("""

                COMPLETED ORDERS:
                -------------""");

        for (Order order : orderManager.completedOrders()) {
            System.out.printf("%n%s%n", order.getOrderLinesInOrder());
        }

        System.out.printf("""
                        Total amount: %.2f kr,-%nOrder Summary (Total Pizzas Sold): %d
                        """,
                orderManager.getTotalIncome(),
                orderManager.getTotalPizzasSold());
    }

    private static void buildMenu() {

        Menu.addPizza(new Product(new Pizza("Vesuvio", "tomatsauce, ost, skinke og oregano"), 57));
        Menu.addPizza(new Product(new Pizza("Amerikaner", "tomatsauce, ost, oksefars og oregano"), 53));
        Menu.addPizza(new Product(new Pizza("Cacciatore", "tomatsauce, ost, pepperoni og oregano"), 57));
        Menu.addPizza(new Product(new Pizza("Carbona", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano"), 63));
        Menu.addPizza(new Product(new Pizza("Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano"), 65));
        Menu.addPizza(new Product(new Pizza("Bertil", "tomatsauce, ost, bacon og oregano"), 57));
        Menu.addPizza(new Product(new Pizza("Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Capriciosa", "tomatsauce, ost, skinke, champignon og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Hawai", "tomatsauce, ost, skinke, ananas og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Le Blissola", "tomatsauce, ost, skinke, rejer og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Venezia", "tomatsauce, ost, skinke, bacon og oregano"), 61));
        Menu.addPizza(new Product(new Pizza("Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano"), 61));
    }

    private static void printMenu() {
        System.out.println(new Menu());
    }

}




