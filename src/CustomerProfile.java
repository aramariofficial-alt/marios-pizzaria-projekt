import java.util.ArrayList;

public class CustomerProfile {
    ArrayList<Order> profileOrders = new ArrayList<>();

    private final int PHONENUMBER;
    private final int PASSWORD;

    public CustomerProfile(int phoneNumber, int password) {
        this.PHONENUMBER = phoneNumber;
        this.PASSWORD = password;

    }

    public int getPhoneNumber() {
        return this.PHONENUMBER;
    }



}
