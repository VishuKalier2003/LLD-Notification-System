package logging.logger.domain.interfaces;

import java.util.Date;

import logging.logger.domain.enums.Logger;

public interface Log {
    // Function to be overriden in subclasses
    public Logger outputLog(String statement, Date date);
}
