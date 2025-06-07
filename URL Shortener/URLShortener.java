import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    URLShortener
    Properties:
    - KeyGenerateStrategy
    - URLRepository
    - ReentrantLock

    Methods:
    + shortenURL(url)  -> Returns shorten URL
    + originalURL(url) -> Returns full URL
*/
public class URLShortener {
    private static URLShortener obj;
    private static Lock lock = new ReentrantLock();
    KeyGenerateStrategy strategy;
    IURLRepository repository;

    private URLShortener (KeyGenerateStrategy strategy, IURLRepository repository) {
        // Constructor dependency injection
        this.strategy = strategy;
        this.repository = repository;
    }

    // Service - Singleton Pattern
    public static URLShortener getURLShortenerInstance () {
        lock.lock();
        if (obj == null) {
            obj = new URLShortener(new Base62Generator(), new URLRepository());
        }
        lock.unlock();
        return obj;
    }

    public String shortenURL (String longURL) {
        if(repository.existsLongURL(longURL)) {
            return repository.getShortURL(longURL);
        }
        // Generate ShortURL
        String urlEnd = strategy.generateKey();
        String shortenURL = "www.bitly.com/"+urlEnd;
        repository.save(longURL, shortenURL);
        return shortenURL;
    }

    public String originalURL (String shortURL) {
        // In C++ we would have to have 2 hashmaps, but in Java we have containsValue(value)
        if (repository.existsShortURL(shortURL)) {
            return repository.getLongURL(shortURL);
        }
        return "Not shortened yet";
    }
}
