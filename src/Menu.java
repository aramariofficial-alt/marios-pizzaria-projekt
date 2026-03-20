import java.util.ArrayList;

public class Menu {

    private static final ArrayList<Product> menu = new ArrayList<>();

    public static void addPizza(Product product) {
        menu.add(product);
    }

    public static Product getPizzaByNumber(int number) {
        if (number < 1 || number > menu.size()) {
            return null;
        }
        return menu.get(number - 1);
    }

    public static int size() {
        return menu.size();
    }
    public static void buildMenu() {

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


    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < menu.size(); i++) {
            Product p = menu.get(i);
            result += (i + 1) + ". " + p.getPizza() + " - " + p.getPrice() + " kr\n";
        }

        return result;
    }
}