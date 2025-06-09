import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    private final Map<String, RateLimiter> userRateLimiter = new ConcurrentHashMap<>();

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
