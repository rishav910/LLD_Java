import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractBrowser implements Browser {
    public Deque<URL> forwardStack = new ArrayDeque<>();
    public Deque<URL> backwardStack = new ArrayDeque<>(); // push(val), pop(), peek(), size()
    public URL currentPage;

    public AbstractBrowser (String url) {
        this.currentPage = new URL(url);
    }

    @Override
    public void forward() {
        if (forwardStack.size() == 0) {
            System.out.println("Cannot move forward");
            return;
        }
        URL backward = currentPage;
        currentPage = forwardStack.peek();
        forwardStack.pop();
        backwardStack.push(backward);
    }

    @Override
    public void backward() {
        if (backwardStack.size() == 0) {
            System.out.println("Cannot move backward");
            return;
        }
        URL forward = currentPage;
        currentPage = backwardStack.peek();
        backwardStack.pop();
        forwardStack.push(forward);
    }

    public void goToNextPage(String url) {
        while (forwardStack.size() > 0) {
            forwardStack.pop();
        }
        backwardStack.push(currentPage);
        currentPage = new URL(url);
    }

    @Override
    public String getCurrentURL() {
        return currentPage.getUrl();
    }

    public abstract String getBrowserName();
}
