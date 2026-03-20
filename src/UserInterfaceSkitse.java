import java.util.ArrayList;
import java.util.Scanner;

public class UserInterfaceSkitse {

    private final OrderManager orderManager = new OrderManager();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        buildMenu();

        while (true) {
            System.out.println("""
                    
                    ===== MARIO'S PIZZARIA =====
                    1. Vis menu
                    2. Opret ordre
                    3. Vis aktive ordrer
                    4. Vis færdige ordrer
                    5. Slet ordre
                    0. Afslut
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> printMenu();
                case 2 -> createOrder();
                case 3 -> printActiveOrders();
                case 4 -> printCompletedOrders();
                case 5 -> deleteOrder();
                case 0 -> {
                    System.out.println("Farvel 👋");
                    return;
                }
                default -> System.out.println("Ugyldigt valg");
            }
        }
    }

//    private void buildMenu() {
//
//        Menu.addPizza(new Product(new Pizza("Vesuvio", "tomatsauce, ost, skinke og oregano"), 57));
//        Menu.addPizza(new Product(new Pizza("Amerikaner", "tomatsauce, ost, oksefars og oregano"), 53));
//        Menu.addPizza(new Product(new Pizza("Cacciatore", "tomatsauce, ost, pepperoni og oregano"), 57));
//        Menu.addPizza(new Product(new Pizza("Carbona", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano"), 63));
//        Menu.addPizza(new Product(new Pizza("Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano"), 65));
//        Menu.addPizza(new Product(new Pizza("Bertil", "tomatsauce, ost, bacon og oregano"), 57));
//        Menu.addPizza(new Product(new Pizza("Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Capriciosa", "tomatsauce, ost, skinke, champignon og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Hawai", "tomatsauce, ost, skinke, ananas og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Le Blissola", "tomatsauce, ost, skinke, rejer og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Venezia", "tomatsauce, ost, skinke, bacon og oregano"), 61));
//        Menu.addPizza(new Product(new Pizza("Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano"), 61));
//    }

    private void printMenu() {
        System.out.println(new Menu());
    }

    private void createOrder() {
        Order order = new Order();

        while (true) {
            printMenu();

            System.out.println("Vælg pizzanummer (0 for færdig):");
            int pizzaNumber = scanner.nextInt();
            scanner.nextLine();

            if (pizzaNumber == 0) {
                break;
            }

            Product selectedProduct = choosePizza(pizzaNumber);

            if (selectedProduct == null) {
                System.out.println("Ugyldigt pizzanummer");
                continue;
            }

            System.out.println("Antal:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            order.addOrderline(new OrderLine(quantity, selectedProduct));
        }

        if (order.getQuantity() == 0) {
            System.out.println("Ordren blev ikke oprettet, fordi der ikke blev valgt nogen pizzaer.");
            return;
        }

        System.out.println("Vil du sætte afhentningstid? (ja/nej)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("ja")) {
            System.out.println("Time:");
            int hour = scanner.nextInt();

            System.out.println("Minut:");
            int minute = scanner.nextInt();
            scanner.nextLine();

            order.addPickUpTime(hour, minute);
        }

        order.setPaid();
        orderManager.addOrder(order);

        System.out.println("Ordre oprettet ✅");
    }

    private Product choosePizza(int pizzaNumber) {
        return switch (pizzaNumber) {
            case 1 -> Menu.getPizzaByNumber(1);
            case 2 -> Menu.getPizzaByNumber(2);
            case 3 -> Menu.getPizzaByNumber(3);
            case 4 -> Menu.getPizzaByNumber(4);
            case 5 -> Menu.getPizzaByNumber(5);
            case 6 -> Menu.getPizzaByNumber(6);
            case 7 -> Menu.getPizzaByNumber(7);
            case 8 -> Menu.getPizzaByNumber(8);
            case 9 -> Menu.getPizzaByNumber(9);
            case 10 -> Menu.getPizzaByNumber(10);
            case 11 -> Menu.getPizzaByNumber(11);
            case 12 -> Menu.getPizzaByNumber(12);
            case 13 -> Menu.getPizzaByNumber(13);
            case 14 -> Menu.getPizzaByNumber(14);
            default -> null;
        };
    }

    private void printActiveOrders() {

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
            System.out.println("\n" + order.getOrderLinesInOrder()
                    + "Time of order: " + order.getTimeOfOrder()
                    + "\nPick up time: " + order.getEffectivePickUpTime());
        }
    }

    private void printCompletedOrders() {

        if (orderManager.completedOrders().isEmpty()) {
            System.out.println("Ingen færdige ordrer");
            return;
        }

        System.out.println("""
                
                COMPLETED ORDERS
                ----------------""");

        for (Order order : orderManager.completedOrders()) {
            System.out.println(order.getOrderLinesInOrder());
        }

        System.out.printf("""
                Total amount: %.2f kr,-
                Total pizzas sold: %d
                """,
                orderManager.getTotalIncome(),
                orderManager.getTotalPizzasSold());
    }

    private void deleteOrder() {

        ArrayList<Order> active = orderManager.activeOrders();

        if (active.isEmpty()) {
            System.out.println("Ingen ordrer at slette");
            return;
        }

        System.out.println("Vælg ordre index:");
        for (int i = 0; i < active.size(); i++) {
            System.out.println(i + ":");
            System.out.println(active.get(i).getOrderLinesInOrder());
        }

        int index = scanner.nextInt();
        scanner.nextLine();

        Order order = active.get(index);
        orderManager.removeOrder(order);

        System.out.println("Ordre slettet ❌");
    }
}