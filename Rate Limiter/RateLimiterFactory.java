public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(String algorithm, int maxRequests, long windowSizeMS ) {
        RateLimiter rateLimiter;
        switch (algorithm) {
            case "token_bucket": rateLimiter = new TokenBucketRateLimiter(maxRequests, ((1.0 * maxRequests)/windowSizeMS));
                                break;
            case "leaky_bucket": rateLimiter = new LeakyBucketRateLimiter(maxRequests, (long)((1.0 * maxRequests)/windowSizeMS));
                                break;
            case "sliding_window": rateLimiter = new SlidingWindowRateLimiter(maxRequests, windowSizeMS);
                                break;
            case "fixed_window": rateLimiter = new FixedWindowRateLimiter(maxRequests, windowSizeMS);
                                break;
            default: throw new IllegalArgumentException("Unsupported type");
        }
        return rateLimiter;
    }
}
