/*
    URLRepository
    Properties:
    - shortToLong map
    - longToShort map
    Methods:
    + existsLongURL(String)
    + existsShortURL(String)
    + getLongURL(String)
    + getShortURL(String)
    + save(long, short)
 */

import java.util.HashMap;
import java.util.Map;

public class URLRepository implements IURLRepository{
    private Map<String, String> longToShort = new HashMap<>();
    private Map<String, String> shortToLong = new HashMap<>();

    @Override
    public boolean existsLongURL(String longURL) {
        if (longToShort.containsKey(longURL)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existsShortURL(String shortURL) {
        if (shortToLong.containsKey(shortURL)) {
            return true;
        }
        return false;
    }

    @Override
    public String getLongURL (String shortURL) {
        return shortToLong.get(shortURL);
    }

    @Override
    public String getShortURL (String longURL) {
        return longToShort.get(longURL);
    }

    @Override
    public void save(String longURL, String shortURL) {
        longToShort.put(longURL, shortURL);
        shortToLong.put(shortURL, longURL);
    }
}
