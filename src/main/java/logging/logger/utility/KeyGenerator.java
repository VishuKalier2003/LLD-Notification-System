package logging.logger.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class KeyGenerator {
    
    private String generator(Date date) {
        long number = (long)((Math.random()*1_000_000_007) + 999_876_457);
        return new StringBuilder().append(date.toString()).append(" ").append(number).reverse().toString();
    }

    public String accessKey(Date date) {
        return generator(date);
    }
}
