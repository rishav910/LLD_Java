
public class Main {
    public static void main(String[] args) {
        // Browser navigation keys (Backward, forward)

        // Entities - Browser (back, forward) < AbstractBrowser (all methods implemented)
        // AbstractBrowser < ChromeBrowser (some abstract methods derived) + super()
        // Child class can call all parent class methods.
        // URL (extension)


        Browser obj = new ChromeBrowser("www.google.com");
        obj.goToNextPage("www.facebook.com");
        obj.goToNextPage("www.splitwise.com");

        System.out.println(obj.getCurrentURL()); // Splitwise

        obj.backward();
        System.out.println(obj.getCurrentURL()); // Facebook
        obj.goToNextPage("www.docs.com");
        System.out.println(obj.getCurrentURL()); // Docs
        obj.forward();                           // Can't move forward
        System.out.println(obj.getCurrentURL()); // Docs
        obj.backward();
        System.out.println(obj.getCurrentURL()); // Facebook

        int k =10;
        while(k>0) {
            obj.backward();
            k--;
        }
        System.out.println(obj.getCurrentURL());
    }
}