public interface Browser {
    public void forward();
    public void backward();
    public String getCurrentURL();
    public void goToNextPage(String url);
    public String getBrowserName();
}
