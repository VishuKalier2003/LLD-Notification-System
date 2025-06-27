package logging.logger.endpoints.dto;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ControllerMessage {
    @NonNull
    private String message;
}
