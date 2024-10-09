package hotelmanagement.users;

public class Customer extends User {
    
    public Customer() {
        super("customer", "");  
    }
    
    @Override
    public String getRole() {
        return role;
    }
}
