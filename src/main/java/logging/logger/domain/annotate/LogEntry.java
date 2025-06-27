package logging.logger.domain.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Enums are field under the hood
@Target({ElementType.FIELD})    // Target scope of Annotation
@Retention(RetentionPolicy.RUNTIME)     // Retention type of Annotation

public @interface       // Annotation 
LogEntry {
    String metaData() default "Test";

    String type() default "None";
}
