public class ChromeBrowser extends AbstractBrowser {

    // Extends -> super() needs to be called
    public ChromeBrowser (String home) {
        super(home);
    }

    public String getBrowserName() {
        return "Google Chrome Browser";
    }

}
