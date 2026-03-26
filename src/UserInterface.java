import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class UserInterface {

    private final Scanner scan = new Scanner(System.in);
    private final Menu menu;
    private final OrderManager orderManager;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final Customers customers;

    public UserInterface(Menu menu, OrderManager orderManager, Customers customers) {
        this.menu = menu;
        this.orderManager = orderManager;
        this.customers = customers;

    }

    public void start() {
        boolean quit = false;
        while (!quit) {

            System.out.println("""
                    1. OPRET BESTILLING
                    2. OPRET BRUGER
                    3. BETAL
                    4. AKTIVE ORDRER
                    5. ADMIN
                    6. AFSLUT
                    """);

            try {
                int choice = scan.nextInt();
                switch (choice) {
                    case 1 -> newOrder();
                    case 2 -> newCustomer();
                    case 3 -> payOrder();
                    case 4 -> printActiveOrders();
                    case 5 -> admin();
                    case 6 -> quit = true;
                    default -> System.out.println("Ugyldigt indtastning, prøv igen: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldigt indtastning, prøv igen: ");
                scan.next();
            }
        }
    }

    private void newCustomer(){
        System.out.println("Indtast navn: ");
        String name = scan.next();
        System.out.println("Indtast telefonnummer: ");
        int phoneNumber = scan.nextInt();
        System.out.println("Indtast password: ");
        int password = scan.nextInt();
        CustomerProfile newCustomer = new CustomerProfile(name, phoneNumber, password);
        customers.addCustomer(newCustomer);

        System.out.println("Brugeren blev oprettet!\n");

    }

    private void payOrder() {

        ArrayList<Order> activeOrders = this.orderManager.activeOrders();

        int count = 1;
        int count2 = 1;

        for (Order order : activeOrders) {
            if (!order.isPaid()) {
                System.out.println(order);

                if (count2 == activeOrders.size()) {
                    System.out.println("Hvilket ordrenummer skal betales?\n(Indtast 0 for at gå tilbage)\n");
                }

                count--;

            } else if (count == activeOrders.size()) {
                System.out.println("--::$$ Alle ordre er betalt $$::--\n");
                return;
            }
            count++;
            count2++;
        }

        while (true) {
            int orderNumber = scan.nextInt();
            if (orderNumber == 0) {
                return; //annullere betaling
            }
            for (Order order : activeOrders) {
                if (order.getOrderNumber() == orderNumber && !order.isPaid()) {
                    System.out.println("1. GÆST\n2. BRUGER");
                    int choice = scan.nextInt();

                        switch(choice){

                        case 1 -> {
                            double totalPrice = orderManager.getOrderTotal(orderNumber);
                            orderManager.payOrder(orderNumber);
                            System.out.println("Ordren er betalt!\nBeløb: " + totalPrice + " kr.\n");
                            return;
                            }
                        case 2 -> {
                            int phoneNumber;
                            while(true) {
                                System.out.println("Indtast telefonnummer: ");
                                phoneNumber = scan.nextInt();
                                System.out.println("Indtast password: ");
                                int password = scan.nextInt();
                                if (customers.isvalid(phoneNumber, password)){
                                    break;
                            }else{
                                System.out.println("Forkert telefonnummer eller password, prøv igen: \n");
                            }
                            }

                            for (CustomerProfile customerProfile : customers.getCustomers()) {
                                    if (customerProfile.getPhoneNumber() == phoneNumber) {
                                        customerProfile.addOrder(order);
                                    }
                                }
                                order.setPaid();
                                double totalPrice = orderManager.getOrderTotal(orderNumber);
                                customerProfile.discount(totalPrice);
                                System.out.println("Ordren er betalt!\nBeløb: " + totalPrice + " kr.\n");
                                return;
                            }

                            default -> System.out.println("Ugyldig indtastning,prøv igen!\n");

                    }
                }
            }
            System.out.println("Ugyldig indtastning, prøv igen!\n (Indtast 0 for at gå tilbage)");
        }
    }

    private void newOrder() {
        Order order = new Order();

        System.out.println(menu);
        boolean orderDone = false;

        try {
            while (!orderDone) {

                System.out.println("Indtast nummer på pizza: ");
                int productNumber = scan.nextInt() - 1;
                while (productNumber > menu.getProductCount()) {
                    System.out.println("Ugyldigt indtastning, prøve igen");
                    productNumber = scan.nextInt();
                }


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

                    System.out.println("""
                            BETALING:
                            1. NU
                            2. SENERE
                            3. NU MED BRUGER
                            """);

                    switch(scan.nextInt()){
                        case 1 -> {
                            order.setPaid();
                            System.out.println("Betaling godkendt\n");
                        }
                        case 2 -> {}

                        case 3 -> {
                            System.out.println("Indtast telefonnummer: ");
                            int phoneNumber = scan.nextInt();
                            System.out.println("Indtast password: ");
                            int password = scan.nextInt();

                            if (customers.isvalid(phoneNumber, password)){
                                for (CustomerProfile customerProfile : customers.getCustomers() ){
                                    if (customerProfile.getPhoneNumber() == phoneNumber){
                                        customerProfile.addOrder(order);
                                    }
                                }
                                order.setPaid();
                            } else {
                                System.out.println("Brugeren eksisterer ikke i systemet");
                            }
                        }

                            default ->  System.out.println("Fejl i input");
                    }
                    orderDone = true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ugyldigt indtastning, prøv igen: ");
            scan.next();
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

        System.out.println("ACTIVE ORDERS:\n--------------------");


        for (Order order : orderManager.activeOrders()) {
            System.out.printf("""
                            --------------------
                            Ordrenummer: %s
                            %s
                            Total: %s kr.
                            
                            Bestil: %s
                            Afhentes: %s
                            Klar: %s
                            Betalt: %s
                            Afhentet: %s
                            --------------------
                            """, order.getOrderNumber(), order.getActiveOrder(), order.getTotal(),
                    order.getTimeOfOrder().format(formatter),
                    order.getEffectivePickUpTime().format(formatter),
                    order.statusReady(), order.statusPaid(), order.statusPickedUp());

        }


        System.out.println("Ønsker du at opdatere en ordre status?" + "\n" + "ja/nej");

        String statusUpdate = scan.next();
        if (statusUpdate.equalsIgnoreCase("nej")) {
            return;
        } else if (statusUpdate.equalsIgnoreCase("ja")) {
            System.out.println("Indtast ordre nummer");
            int orderNumber = scan.nextInt();
            Order chosenOrder = orderManager.getActiveOrderByOrderNumber(orderNumber);
            System.out.printf("""
                            %s
                            Klar: %s
                            Betalt: %s
                            Afhentet: %s
                            
                            """, chosenOrder, chosenOrder.statusReady(), chosenOrder.statusPaid(),
                    chosenOrder.statusPickedUp());


            System.out.println("""
                    Hvilken handling vil du udføre?
                    1. KLAR
                    2. BETALT
                    3. AFHENTET
                    4. ANNULLER ORDRE
                    """);

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
                    if (chosenOrder.isPaid() && chosenOrder.isReady()) {
                        chosenOrder.setPickedUp();
                        System.out.println("Ordren er nu afhentet og afsluttet");
                    }
                    if (!chosenOrder.isPaid()) {

                        System.out.println("Ordren mangler betaling!");
                    }
                    if (!chosenOrder.isReady()) {

                        System.out.println("Ordren er ikke klar!");

                    }
                    System.out.println();
                }


            case 4 -> {
                chosenOrder.setCancelled();
                System.out.println("Ordren blev annulleret.");

            }

            default -> System.out.println("Ugyldig indtastning, prøv igen: \n");
        }

    }

}

private void admin() {
    System.out.println("""
            1. ÆNDRE PRIS I MENU
            2. STATISTIK
            """);

    int choice = scan.nextInt();

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
                    1. OMSÆTNING
                    2. MEST SOLGTE PRODUKTER
                    3. AFSLUTTEDE ORDRER
                    4. ANNULLEREDE ORDRER
                    5. EKSISTERENDE BRUGERE
                    """);

            int choice2 = scan.nextInt();

            switch (choice2) {

                case 1 -> {
                    System.out.println("Antal pizzaer solgt: " + orderManager.getTotalPizzasSold());
                    System.out.println("Omsætning: " + orderManager.getTotalIncome() + " kr.");
                    System.out.println();
                }
                case 2 -> orderManager.pizzaRanking(menu.getMenu());

                case 3 -> orderManager.printCompletedOrders();

                case 4 -> {
                    if (orderManager.cancelledOrders().isEmpty()) {
                        System.out.println("Ingen annullerede ordrer i systemet på nuværende tidspunkt" + "\n");
                        break;
                    }
                    System.out.println("Anullerede ordrer" + "\n------------" + "\n" +
                            orderManager.cancelledOrdersToString());
                }
                case 5 -> {
                    System.out.println(customers.toString());


                }

                default -> System.out.println("Ugyldig indtastning, prøv igen: \n");
            }

        }

        default -> System.out.println("Ugyldig indtastning, prøv igen: \n");
    }
}
}



