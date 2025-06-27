package logging.logger.domain.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import logging.logger.domain.enums.Logger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("DataSheet")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataModel {
    @Id
    private String uuid;
    @NonNull
    private Logger logger;
    @NonNull
    private Date date;
}
