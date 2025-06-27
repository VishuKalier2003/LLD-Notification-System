package logging.logger.service.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import logging.logger.domain.annotate.Api;
import logging.logger.utility.Printer;

@Aspect
@Component
public class ApiAspect {

    private static final String SOURCE = "ApiAspect.java";

    @Autowired
    private Printer printer;
    
    @Pointcut("@annotation(api)")
    public void binding(Api api) {}

    @Before("binding(api)")
    public void beforeAspect(Api api) {
        printer.printSingle();
        printer.printWord(SOURCE, "Api code "+api.code()+" pushed from "+api.mapping());
    }

    @AfterReturning(pointcut="binding(api)", returning="result")
    public Object afterAspect(Api api, Object result) {
        printer.printSingle();
        printer.printWord(SOURCE, "Api Test Completed!!\n Api of type "+api.type()+" successfully pushed (@AfterReturning)");
        return result;
    }

    // Only @Around can intercept the return of the business logic via try catch
    @Around("binding(api)")
    public Object aroundApi(ProceedingJoinPoint pjp, Api api) throws Throwable {
        try {
            Object result = pjp.proceed();
            printer.printSingle();
            printer.printWord(SOURCE, "Api of type "+api.type()+" successfully pushed (@Around)");
            return result;
        } catch (MethodArgumentNotValidException ex) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method Arguments not valid !!");
        } catch (HttpMessageNotReadableException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Http Message not readable !!");
        } catch (HttpRequestMethodNotSupportedException ex) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Method type is not valid !!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Undefined error found !!");
        }
    }
}
