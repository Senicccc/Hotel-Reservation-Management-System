package hotelmanagement.users;

public class User {
    protected String role;
    private String password;

    public User(String role, String password) {
        this.role = role;
        this.password = password;
    }

    public boolean login(String passwordInput) {
        return this.password.equals(passwordInput);
    }

    public String getRole() {
        return role;
    }
}
