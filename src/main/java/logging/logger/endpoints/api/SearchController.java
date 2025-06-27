package logging.logger.endpoints.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logging.logger.domain.annotate.Api;
import logging.logger.endpoints.dto.ControllerMessage;
import logging.logger.facade.Facade;

@RestController
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    private Facade facade;

    @Api(type="Get", code=0, mapping="http://localhost:8080/search/logSheet/all")
    @GetMapping("/logSheet/all")
    public ResponseEntity<Object> searchlogs() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facade.searchInLogSheet());
    }

    @Api(type="Get", code=0, mapping="http://localhost:8080/search/dataSheet/all")
    @GetMapping("/dataSheet/all")
    public ResponseEntity<Object> searchdata() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facade.searchInDataSheet());
    }

    @Api(type="Get", code=0, mapping="http://localhost:8080/search/logSheet")
    @GetMapping("/logSheet")
    public ResponseEntity<Object> searchlogs(@RequestBody ControllerMessage cm) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facade.searchInLogSheet(cm.getMessage()));
    }

    @Api(type="Get", code=0, mapping="http://localhost:8080/search/dataSheet")
    @GetMapping("/dataSheet")
    public ResponseEntity<Object> searchdata(@RequestBody ControllerMessage cm) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facade.searchInDataSheet(cm.getMessage()));
    }
}
