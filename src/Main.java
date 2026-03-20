
public class Main {
    public static void main(String[] args) {

        Menu menu = buildMenu();
        OrderManager orderManager = new OrderManager();
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




