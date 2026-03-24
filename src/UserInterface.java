import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    // public static void main(String[] args) {
    private final Scanner scan = new Scanner(System.in);
    private final Menu menu;
    private final OrderManager orderManager;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm");

    public UserInterface(Menu menu, OrderManager orderManager) {
        this.menu = menu;
        this.orderManager = orderManager;

    }

    public void start() {
        boolean quit = false;
        while (!quit) {

            System.out.println("""
                    1. Opret Bestilling
                    2. Betal
                    3. Aktive Ordrer
                    4. Admin
                    5. Afslut
                    """);

            try{
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> newOrder();
                case 2 -> payOrder();
                case 3 -> printActiveOrders();
                case 4 -> admin();
                case 5 -> quit = true;
                default -> System.out.println("Ugyldigt nummer, prøv igen: ");
            }
            }catch (InputMismatchException e){
                System.out.println("Ugyldigt nummer, prøv igen: ");
                scan.next();
            }
        }
    }

    private void payOrder() {
        ArrayList<Order> activeOrders = this.orderManager.activeOrders();
        System.out.println("Hvilken ordre bliver betalt");
        System.out.println("0 for at annullere");
        System.out.println();
        activeOrders.forEach(order -> {
            System.out.println(order.getOrderNumberString());
        });
        int choice = scan.nextInt();
        if (choice == 0) {
            return; //annullere betaling
        }
        double totalPrice = orderManager.getOrderTotal(choice);
        System.out.println("Total: " + totalPrice);
        orderManager.payOrder(choice);

    }

    private void newOrder() {
        Order order = new Order();

        System.out.println(menu);
        boolean orderDone = false;

        while (!orderDone) {

            System.out.println("Indtast nummer på pizza: ");
            int productNumber = scan.nextInt() - 1;

            Product chosenProduct = menu.getProductByNumber(productNumber);

            System.out.println("Indtast antal: ");
            int quantity = scan.nextInt();

            order.addOrderline(new OrderLine(quantity, chosenProduct));

            int choice;
            while (true) { //sikrer korrekt input
                System.out.println("Tryk 0 for at afslutte bestillingen");
                System.out.println("Tryk 1 for at tilføje flere pizzaer");
                choice = scan.nextInt();

                if (choice == 0 || choice == 1) {
                    break;
                }
                System.out.println("Fejl i input. Prøv igen");


            }


            if (choice == 1) {
                System.out.println(menu);
                continue;
            }

            if (choice == 0) {

                System.out.println("Tilføj afhentningstid? (ja/nej)");
                if (scan.next().equalsIgnoreCase("ja")) {
                    System.out.println("Time: ");
                    int time = scan.nextInt();

                    System.out.println("Minut: ");
                    int minut = scan.nextInt();

                    order.addPickUpTime(time, minut);
                }

                System.out.println("Betal nu? (ja/nej)");
                if (scan.next().equalsIgnoreCase("ja")) {
                    order.setPaid();
                    System.out.println("Betaling godkendt\n");
                }

                orderDone = true;
            } else {
                System.out.println("Fejl i input");
            }
        }

        orderManager.addOrder(order);

        System.out.println("Ordren er nu oprettet\n" + order);
    }

    private void printActiveOrders() {
        if (orderManager.activeOrders().isEmpty()) {
            System.out.println("""
                    ACTIVE ORDERS: 0
                    -------------""");
            return;
        }

        System.out.println("ACTIVE ORDERS:\n------------");

        int i = 0;
        for (Order order : orderManager.activeOrders()) {
            System.out.println("Ordrenummer: " + (order.getOrderNumber()) + "\n" + order.getActiveOrder() + "\n" + "Total: " + order.getTotal() + "kr,-" + "\n\n" + "Bestilt: " +
                    order.getTimeOfOrder().format(formatter) + "\n" + "Afhentes: " +
                    order.getEffectivePickUpTime().format(formatter) + "\n" + "Klar? " + order.isReady() + "\n" +
                    "Betalt? " + order.isPaid() + "\n\n");
            i++;
        }


        System.out.println("Ønsker du at opdatere en ordre status?" + "\n" + "ja/nej");

        String statusUpdate = scan.next();
        if (statusUpdate.equalsIgnoreCase("nej")) {
            return;
        } else if (statusUpdate.equalsIgnoreCase("ja")) {
            System.out.println("Indtast ordre nummer");
            int orderNumber = scan.nextInt();
            Order chosenOrder = orderManager.getActiveOrderByOrderNumber(orderNumber);
            System.out.println((chosenOrder) + "\n" + "Klar? " + chosenOrder.isReady() + "\n" +
                    "Betalt? " + chosenOrder.isPaid() + "\n\n");


            System.out.println("""
                    Hvilken handling vil du udfører?
                    1. Ordre klar
                    2. Ordre betalt
                    3. Annuller ordre""");

            int choice = scan.nextInt();

            switch (choice) {

                        case 1 -> {
                            chosenOrder.setReady();
                            System.out.println("Ordren er nu klar");
                        }
                        case 2 -> {
                            chosenOrder.setPaid();
                            System.out.println("Ordren er nu betalt");
                        }
                        case 3 -> {
                            chosenOrder.setCancelled();
                            System.out.println("Ordren blev annulleret.");
                        }

                default -> System.out.println("Input fejl");
            }

        }

    }

    private void admin() {
        System.out.println("""
                1. Ændre menu
                2. Vis statistik""");

        int choice = scan.nextInt();

//        switch (choice) {
//            //case 1, 2 -> {
//                //switch (choice){
//            case 1 -> {
//                System.out.println(menu);
//
//                System.out.println("""
//                                1. Ændre pris
//                                2. Tilføj pizza""");

        switch (choice) {
            case 1 -> {
                System.out.println(menu);
                System.out.println("Hvilken pizza nr. vil du ændre prisen på?");
                int productNumber = scan.nextInt() - 1;
                Product chosenProduct = menu.getProductByNumber(productNumber);
                System.out.println("Hvad skal den nye pris på " + chosenProduct.getPizza() + " være?");
                int newPrice = scan.nextInt();
                chosenProduct.setPrice(newPrice);
                System.out.println("Prisen på " + chosenProduct.getPizza() + " er ændret til " + newPrice + " kr.");
                System.out.println();
            }
            case 2 -> {
                System.out.println("""
                        1. Vis omsætning
                        2. Vis mest solgte pizzaer
                        3. Vis afsluttede ordre
                        4. Vis annullerede ordre""");

                int choice2 = scan.nextInt();

                switch (choice2) {

                    case 1 -> {
                        System.out.println("Antal pizzaer solgt: " + orderManager.getTotalPizzasSold());
                        System.out.println("Dagens omsætning: " + orderManager.getTotalIncome() + " kr.");
                        System.out.println();
                    }

                    case 2 -> {
                        orderManager.pizzaRanking(menu.getMenu());


                    }
                    case 3 -> {
                        orderManager.printCompletedOrders();

                    }
                    case 4 -> {
                        if (orderManager.cancelledOrders().isEmpty()){
                            System.out.println("Ingen annullerede ordrer i systemet på nuværende tidspunkt" + "\n");
                            break;
                        }
                        System.out.println("Anullerede ordrer" + "\n------------" + "\n" +
                                orderManager.cancelledOrdersToString());
                    }

                    default -> System.out.println("Fejl i input");
                }

            }

            default -> System.out.println("Fejl i input");
        }
    }
}



