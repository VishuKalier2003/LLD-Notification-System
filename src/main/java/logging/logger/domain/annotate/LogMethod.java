package logging.logger.domain.annotate;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethod {
    String startLog() default "";

    String endLog() default "";
}
