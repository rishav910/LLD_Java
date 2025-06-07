/*
    IURLRepository
    Concrete implementation in URLRepository
 */
public interface IURLRepository {
    public boolean existsLongURL(String longURL);

    public boolean existsShortURL(String shortURL);

    public String getLongURL (String shortURL);

    public String getShortURL (String longURL);

    public void save(String longURL, String shortURL);
}
