package logging.logger.endpoints.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logging.logger.domain.annotate.Api;
import logging.logger.endpoints.dto.ControllerMessage;
import logging.logger.facade.Facade;

@RestController
@RequestMapping("/log")
public class Controller {

    @Autowired
    private Facade facade;

    private static final String PASS = "Passed";

    @Api(type="Post", code=1, mapping="http://localhost:8080/log/info")
    @PostMapping("/info")
    public ResponseEntity<Object> info(@RequestBody ControllerMessage cm) {
        facade.logInfo(cm.getMessage());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PASS);
    }

    @Api(type="Post", code=1, mapping="http://localhost:8080/log/warn")
    @PostMapping("/warn")
    public ResponseEntity<Object> warn(@RequestBody ControllerMessage cm) {
        facade.logWarn(cm.getMessage());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PASS);
    }

    @Api(type="Post", code=1, mapping="http://localhost:8080/log/error")
    @PostMapping("/error")
    public ResponseEntity<Object> error(@RequestBody ControllerMessage cm) {
        facade.logError(cm.getMessage());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PASS);
    }

    @Api(type="Put", code=2, mapping="http://localhost:8080/mapping")
    @PutMapping("/mapping")
    public ResponseEntity<Object> mapping() {
        facade.showLogs();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(PASS);
    }
}
