import java.util.ArrayList;

public class Menu {

    private static ArrayList<Product> menu = new ArrayList<>();

    public static void addPizza(Product product){
        menu.add(product);

    }
    public ArrayList<Product> getMenu(){
        return menu;
    }
    public Product chooseMenuNumber(int number){
        return menu.get(number);
    }

    @Override
    public String toString() {
        String result = "";
        int i = 1;

        for (Product p : menu) {
            result += i + ". " + p.getPizza() + " : " + p.getIngredients() + " - " + p.getPrice() + " kr\n";
        i++;
        }

        return result;
    }

}
