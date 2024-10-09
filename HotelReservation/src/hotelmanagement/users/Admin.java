package hotelmanagement.users;

public class Admin extends User {
    
    public Admin() {
        super("admin", "admin123"); 
    }
    
    @Override
    public String getRole() {
        return role;
    }
}
