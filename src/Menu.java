import java.util.ArrayList;

public class Menu {

    static ArrayList<Product> menu = new ArrayList<>();

    public static void addPizza(Product product){
        menu.add(product);

    }

    public String toString(){
       return String.format("""
                %s
                """, menu);
    }
}
