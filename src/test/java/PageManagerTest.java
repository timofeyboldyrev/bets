import org.junit.Test;
import ru.boldyrev.http.PageManager;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
public class PageManagerTest {

    @Test
    public void printPageContent() throws MalformedURLException {
        printPageContent("https://www.marathonbettinres.com/su/live/26418");
    }

    private void printPageContent(String address) throws MalformedURLException {
        URL url = new URL(address);
        String pageContent = new PageManager().loadPageContent(url);
        System.out.println(pageContent);
    }

}
