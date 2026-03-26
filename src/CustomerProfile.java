import java.util.ArrayList;

public class CustomerProfile {
    ArrayList<Order> profileOrders = new ArrayList<>();

    private final String NAME;
    private final int PHONENUMBER;
    private final int PASSWORD;

    public int profileTotalOrders() {
        return profileOrders.size();
    }

    public double profileTotalMoneySpent() {
        double spendings = 0;

        for (Order order : profileOrders) {
            spendings += order.getTotal();
        }

        return spendings;
    }

    public CustomerProfile(String name, int phoneNumber, int password) {
        this.NAME = name;
        this.PHONENUMBER = phoneNumber;
        this.PASSWORD = password;

    }

    public String getNAME() {
        return NAME;
    }

    public int getPhoneNumber() {
        return this.PHONENUMBER;
    }

    public int getPASSWORD() {
        return PASSWORD;
    }

    public ArrayList<Order> getProfileOrders() {
        return profileOrders;
    }

    public void addOrder(Order order) {
        profileOrders.add(order);
    }


    @Override
    public String toString() {
        return String.format("""
                Navn: %s
                TelefonNummer: %d
                """, getNAME(), getPhoneNumber());
    }
}
