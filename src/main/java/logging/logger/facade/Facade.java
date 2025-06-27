package logging.logger.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.logger.domain.model.DataModel;
import logging.logger.domain.model.LogModel;
import logging.logger.service.business.LogService;
import logging.logger.service.business.SearchService;

@Service
public class Facade {
    @Autowired
    private LogService logService;

    @Autowired
    private SearchService searchService;

    public void logInfo(String message) {
        logService.infoCall(message, new Date());
    }

    public void logWarn(String message) {
        logService.warnCall(message, new Date());
    }

    public void logError(String message) {
        logService.errorCall(message, new Date());
    }

    public void showLogs() {
        logService.mapping();
    }

    public List<DataModel> searchInDataSheet() {
        return searchService.searchInDataSheet();
    }

    public List<LogModel> searchInLogSheet() {
        return searchService.searchInLogModel();
    }

    public DataModel searchInDataSheet(String uuid) {
        return searchService.searchInDataModel(uuid);
    }

    public LogModel searchInLogSheet(String uuid) {
        return searchService.searchInLogModel(uuid);
    }
}
