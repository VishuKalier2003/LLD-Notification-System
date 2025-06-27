package logging.logger.service.aspects;

import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import logging.logger.domain.annotate.LogEntry;
import logging.logger.domain.annotate.LogMethod;
import logging.logger.domain.enums.Logger;
import logging.logger.utility.Printer;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private Printer printer;

    private static final String SOURCE = "LogAspect.java";

    // Selects all functions with annotation "LogMethod" to advise
    @Pointcut("@annotation(logMethod)")     // pass the parameter name instead of annotation name
    public void logFunction(LogMethod logMethod) {}

    @AfterThrowing(pointcut="logFunction(logMethod)", throwing="ex")
    public void afterAdvice(LogMethod logMethod, Exception ex) {
        printer.printSingle();
        printer.printWord(SOURCE, "Unexpected Exception in (LogAspect.java) file");
        Throwable cause = ex.getCause();
        if (cause != null)      // If case is not null
            System.out.println("UNEXPECTED EXCEPTION CAUSE : " + cause);
    }

    @AfterReturning(pointcut="logFunction(logMethod)", returning="result")
    public Object afterReturn(LogMethod logMethod, Object result) throws NoSuchFieldException {
        if(result instanceof Logger log) {
            Field f = Logger.class.getField(log.name());
            LogEntry entry = f.getAnnotation(LogEntry.class);
            if(entry != null) {
                printer.printSingle();
                printer.printWord(SOURCE, "Log type completed "+entry.type());
                printer.printWord(SOURCE, "Log details "+entry.type());
            } else {
                printer.printSingle();
                printer.printWord(SOURCE, "No correct log type found !! Error in Log Types (File LogAspect.java)");
            }
        }
        return result;
    }

    @Around("logFunction(logMethod)")
    public Object around(ProceedingJoinPoint pj, LogMethod logMethod) throws Throwable {
        long start = System.currentTimeMillis();
        Object res;
        try {res = pj.proceed();}       // Invoking the business logic
        catch(Exception e) {throw e;}
        long end = System.currentTimeMillis();
        printer.printSingle();
        printer.printWord(SOURCE, "Start at "+start+" ending at "+end+" with duration : "+(end-start));
        return res;
    }

}
