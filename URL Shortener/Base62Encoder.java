/*
    Base62Encoder

    Properties:
    - 62 chars string

    Methods:
    + key(n) override
 */

public class Base62Encoder {
    private static final String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String key(int n) {
        String str = "";
        while(n > 0) {
            int x = n%62;
            str += s.charAt(x);
            n/=62;
        }
        return str;
    }
}
