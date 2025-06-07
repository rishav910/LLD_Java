public class Main {
    public static void main(String[] args) {
        var obj = URLShortener.getURLShortenerInstance();

        String url1 = "https://github.com/rishav910/Low-Level-Design/blob/main/URLshortener.cpp";
        String shortURL1 = obj.shortenURL(url1);

        String url2 = "https://github.com/rishav910/LLD_Java";
        String shortURL2 = obj.shortenURL(url2);

        System.out.println(shortURL1);
        System.out.println(shortURL2);

        System.out.println(obj.originalURL("www.google.com"));
        System.out.println(obj.originalURL(shortURL1));

    }
}