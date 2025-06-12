public class User {
    private String name;
    private static int userID = 0;
    private String emailID;
    private String password;
    private Role role;

    // Separate constructor for admin roles/if specified
    public User(String name, String emailID, String password, Role role) {
        this.name = name;
        this.emailID = emailID;
        this.password = password;
        this.role = role;
        userID++;
    }

    public User(String name, String emailID, String password) {
        this.name = name;
        this.emailID = emailID;
        this.password = password;
        this.role = Role.CUSTOMER;
        userID++;
    }

    public boolean isEqualPassword (String newPass) {
        if (password.equals(newPass)) {
            return true;
        }
        return false;
    }

    public String getName () {
        return this.name;
    }

    public int getUserID () {
        return userID;
    }

    public String getEmailID () {
        return emailID;
    }

    public Role getRole () {
        return this.role;
    }
}
