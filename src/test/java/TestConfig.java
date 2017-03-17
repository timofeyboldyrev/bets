import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.boldyrev.config.Context;

/**
 * Created by t.boldyrev on 16.03.2017.
 */
@Configuration
@Import(Context.class)
public class TestConfig {
}
