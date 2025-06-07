/*
    Base62Generator

    Properties:
    - static counter = 0
    - 62 character string

    Methods:
    - key(counter)
    + generateKey() override
*/
public class Base62Generator implements KeyGenerateStrategy {
    private static int counter = 190;
    private static final String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String key(int n) {
        String str = "";
        while(n > 0) {
            int x = n%62;
            str += s.charAt(x);
            n/=62;
        }
        return str;
    }

    @Override
    public String generateKey() {
        counter++;
        return key(counter);
    }


}
