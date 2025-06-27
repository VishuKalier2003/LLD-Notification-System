package logging.logger.domain.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The metadata for the Controllers storing the type of request, code of request and the url of mapping
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface // annotation
Api {
    String type() default "";
    int code() default -1;
    String mapping() default "/";
}
