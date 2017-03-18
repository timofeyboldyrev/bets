import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.boldyrev.db.DbManager;

/**
 * Created by Timofey Boldyrev on 18.03.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DbTest {

    @Autowired private DbManager dbManager;

    @Test
    public void test() {

    }
}
