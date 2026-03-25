
public class Main {
    public static void main(String[] args) {



        Menu menu = buildMenu();
        OrderManager orderManager = new OrderManager();
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        Order order5 = new Order();
        Order order6 = new Order();

        order1.addOrderline(new OrderLine(13, menu.getProductByNumber(11)));
        order1.addOrderline(new OrderLine(2, menu.getProductByNumber(1)));
        order2.addOrderline(new OrderLine(4, menu.getProductByNumber(12)));
        order2.addOrderline(new OrderLine(1, menu.getProductByNumber(3)));
        order3.addOrderline(new OrderLine(6, menu.getProductByNumber(7)));
        order4.addOrderline(new OrderLine(2, menu.getProductByNumber(8)));
        order5.addOrderline(new OrderLine(6, menu.getProductByNumber(3)));
        order6.addOrderline(new OrderLine(2, menu.getProductByNumber(10)));

        order1.setPaid();
        order1.setReady();
        order2.setPaid();
        order2.setReady();
        order2.setPickedUp();
        order3.setPaid();
        order3.setReady();
        order3.setPickedUp();
        order4.setPaid();
        order5.setReady();
        order6.setPaid();
        order6.setReady();
        order6.setPickedUp();


        orderManager.addOrder(order1);
        orderManager.addOrder(order2);
        orderManager.addOrder(order3);
        orderManager.addOrder(order4);
        orderManager.addOrder(order5);
        orderManager.addOrder(order6);




        UserInterface ui = new UserInterface(menu, orderManager);
        ui.start();

    }

    public static Menu buildMenu() {
        Menu menu = new Menu();
        menu.addProduct(new Product(new Pizza("Vesuvio", "tomatsauce, ost, skinke og oregano"), 57));
        menu.addProduct(new Product(new Pizza("Amerikaner", "tomatsauce, ost, oksefars og oregano"), 53));
        menu.addProduct(new Product(new Pizza("Cacciatore", "tomatsauce, ost, pepperoni og oregano"), 57));
        menu.addProduct(new Product(new Pizza("Carbona", "tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano"), 63));
        menu.addProduct(new Product(new Pizza("Dennis", "tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano"), 65));
        menu.addProduct(new Product(new Pizza("Bertil", "tomatsauce, ost, bacon og oregano"), 57));
        menu.addProduct(new Product(new Pizza("Silvia", "tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Victoria", "tomatsauce, ost, skinke, ananas, champignon, løg og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Toronfo", "tomatsauce, ost, skinke, bacon, kebab, chili og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Capriciosa", "tomatsauce, ost, skinke, champignon og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Hawai", "tomatsauce, ost, skinke, ananas og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Le Blissola", "tomatsauce, ost, skinke, rejer og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Venezia", "tomatsauce, ost, skinke, bacon og oregano"), 61));
        menu.addProduct(new Product(new Pizza("Mafia", "tomatsauce, ost, pepperoni, bacon, løg og oregano"), 61));
        return menu;
    }
}




