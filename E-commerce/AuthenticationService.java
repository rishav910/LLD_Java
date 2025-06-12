import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Boolean> loggedIn = new ConcurrentHashMap<>();
    private final Map<String, Amazon> userSpecificAmazon = new ConcurrentHashMap<>();

    public AuthenticationService () {}

    // Register user
    public void registerUser (String name, String emailID, String password) {
        User newUser = new User(name, emailID, password, Role.CUSTOMER);
        users.put(emailID, newUser);
        loggedIn.put(emailID, false);
        System.out.println("Registration of user " + name + " successful");
    }

    // Register user (Overload - for role based)
    public void registerUser (String name, String emailID, String password, Role role) {
        User newUser = new User(name, emailID, password, role);
        users.put(emailID, newUser);
        loggedIn.put(emailID, false);
        System.out.println("Registration of user " + name + " successful");
    }

    // Login user
    public void loginUser (String emailID, String password) {
        if (!users.containsKey(emailID)) {
            System.out.println("Not registered");
            return;
        }
        User user = users.get(emailID);
        if (user.isEqualPassword(password)) {
            System.out.println("Successful log in");
        }
        loggedIn.put(emailID, true);
    }

    // Is Logged in?
    public boolean isLoggedIn (String emailID) {
        if (!loggedIn.containsKey(emailID)) {
            return false;
        }
        return loggedIn.get(emailID);
    }

    // Get Amazon instance
    public Amazon openAmazon (User user) {
        if (!loggedIn.containsKey(user.getEmailID())) {
            System.out.println("Unregistered user");
            return null;
        }
        if (loggedIn.get(user.getEmailID()) == false) {
            System.out.println("User " + user.getName() + " not logged in");
            return null;
        }
        if (userSpecificAmazon.containsKey(user.getEmailID())) {
            return userSpecificAmazon.get(user.getEmailID());
        }

        Amazon amazon = new Amazon();
        userSpecificAmazon.put(user.getEmailID(), amazon);
        return amazon;
    }

    // Get User
    public User getUser (String emailID) {
        if (!users.containsKey(emailID)) {
            System.out.println("User not registered yet. Please register to get access");
        }
        return users.get(emailID);
    }
}
