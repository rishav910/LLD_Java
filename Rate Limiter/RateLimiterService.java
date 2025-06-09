import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    private final Map<String, RateLimiter> userRateLimiter = new ConcurrentHashMap<>();
    private static RateLimiterService service;

    // Private constructor (Singleton)
    private RateLimiterService () {
    }

    // Private copy constructor (Singleton)
    private RateLimiterService (RateLimiterService other) {
    }

    public static synchronized RateLimiterService getInstance () {
        // Singleton - Private constructor, static getInstance(), static object
        if (service == null) {
            service = new RateLimiterService();
        }
        return service;
    }

    public void registerUser(String userID, String algorithm, int maxRequests, long windowSizeSeconds) {
        userRateLimiter.put(userID, RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds*1000));
    }

    public boolean allowRequest(String userID) {
        if(!userRateLimiter.containsKey(userID)) {
            throw new IllegalArgumentException("User not registered");
        }
        RateLimiter obj = userRateLimiter.get(userID);
        return obj.allowRequest();
    }
}
