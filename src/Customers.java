import java.util.ArrayList;

public class Customers {
    private ArrayList<CustomerProfile> customers = new ArrayList<>();

    public ArrayList<CustomerProfile> getCustomers() {
        return customers;
    }

    public void addCustomer(CustomerProfile newCustomer) {
        customers.add(newCustomer);

    }


    public boolean isvalid(int phoneNumber, int password) {

        for (CustomerProfile customerProfile : customers) {
            if (phoneNumber == customerProfile.getPhoneNumber() && password == customerProfile.getPASSWORD()) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        String s = "";
        s += "Eksisterende brugere" + "\n " + "----------" + "\n";

        for (CustomerProfile customerProfile : customers) {
            s +=  "Navn: " + customerProfile.getNAME() +
                    "\n" + "Tlf: " + customerProfile.getPhoneNumber() + "\n" + "Antal ordre: " +
                    customerProfile.profileTotalOrders() + "\n" + "Total beløb: " +
                    customerProfile.profileTotalMoneySpent() + " kr." +  "\n\n" ;
        }
        s += "----------";

        return s;
    }
}

