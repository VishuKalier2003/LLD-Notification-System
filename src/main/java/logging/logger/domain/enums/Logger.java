package logging.logger.domain.enums;

import java.util.Date;

import org.springframework.stereotype.Component;

import logging.logger.domain.annotate.LogEntry;
import logging.logger.domain.annotate.LogMethod;
import logging.logger.domain.interfaces.Log;
import lombok.Getter;

/**
 * Logger enum which defines the type of Log as <b>INFO</b>, <b>WARN</b> and <b>ERROR</b>, and each has a overriden function of <i>outputLog(String sentence, Date date)</i> to store the message and the date in the enum itself at run time
 */
// Marking as Component (Singleton)
@Component
@Getter
public enum Logger implements Log {
    // Here each enum defined INFO, WARN and ERROR is a Singleton as well

    // Enums are actually fields of the class hence the annotation scope is set FIELD
    @LogEntry(metaData="INFO LOG passed successfully", type="Info")
    INFO {
        @Override
        @LogMethod(startLog="Info log started...", endLog="Info log completed...")
        public Logger outputLog(String sentence, Date date) {
            this.logDate = date;
            this.message = "INFO : "+date.toString()+" : "+sentence;
            return INFO;
        }
    }, 
    @LogEntry(metaData="WARNING LOG traced successfully", type="Warn")      // passing meta data
    WARN {
        @Override
        @LogMethod(startLog="Warn log stated...", endLog="Warn log ended...")
        public Logger outputLog(String sentence, Date date) {
            this.logDate = date;
            this.message =  """
                    There is a MINOR issue, please review it !!\n 
                    WARN : """+date.toString()+" : "+sentence;
            return WARN;
        }
    },
    @LogEntry(metaData="ERROR LOG found promptly", type="Error")
    ERROR {
        @Override
        @LogMethod(startLog="Error log started...", endLog="Error log terminated...")
        public Logger outputLog(String sentence, Date date) {
            this.logDate = date;
            this.message = """
                    There is Critical BUG in the system, check and resolve it fast\n
                    ERROR : """+date.toString()+" : "+sentence;
            return ERROR;
        }
    };

    protected Date logDate;
    protected String message;
}
