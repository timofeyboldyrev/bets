import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.boldyrev.http.PageManager;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PageManagerTest {

    @Autowired private PageManager pageManager;

    @Test
    public void printPageContent() throws MalformedURLException {
//        printPageContent("https://www.tinkoff.ru");
        printPageContent("https://www.marathonbetmarathonbet.com/su/live/26418");
    }

    private void printPageContent(String address) throws MalformedURLException {
        URL url = new URL(address);
        String pageContent = pageManager.loadPageContent(url);
        System.out.println("Page content:");
        System.out.println(pageContent);
    }

}
