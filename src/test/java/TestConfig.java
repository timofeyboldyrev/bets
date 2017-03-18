import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Timofey Boldyrev on 16.03.2017.
 */
@Configuration
@ComponentScan("ru.boldyrev")
@PropertySource("application.properties")
public class TestConfig {

}
