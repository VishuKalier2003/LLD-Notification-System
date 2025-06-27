package logging.logger.service.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.logger.config.Mapper;
import logging.logger.domain.annotate.LogMethod;
import logging.logger.domain.enums.Logger;
import logging.logger.domain.model.DataModel;
import logging.logger.domain.model.LogModel;
import logging.logger.repo.DataRepo;
import logging.logger.repo.LogRepo;
import logging.logger.utility.KeyGenerator;

@Service
public class LogService {
    @Autowired
    private DataRepo dataRepo;

    @Autowired
    private LogRepo logRepo;

    @Autowired
    private Mapper mapper;

    @Autowired
    private KeyGenerator generator;
    
    @LogMethod()
    public Logger infoCall(String message, Date date) {
        String id = generator.accessKey(date);
        DataModel dm = new DataModel(id, Logger.INFO.outputLog(message, date), date);
        dataRepo.save(dm);
        logRepo.save(new LogModel(id, Logger.INFO.outputLog(message, date), date));
        mapper.addToMap(dm);
        return Logger.INFO;
    }

    @LogMethod()
    public Logger warnCall(String message, Date date) {
        String id = generator.accessKey(date);
        DataModel dm = new DataModel(id, Logger.WARN.outputLog(message, date), date);
        dataRepo.save(dm);
        logRepo.save(new LogModel(id, Logger.WARN.outputLog(message, date), date));
        mapper.addToMap(dm);
        return Logger.WARN;
    }

    @LogMethod()
    public Logger errorCall(String message, Date date) {
        String id = generator.accessKey(date);
        DataModel dm = new DataModel(id, Logger.ERROR.outputLog(message, date), date);
        dataRepo.save(dm);
        logRepo.save(new LogModel(id, Logger.ERROR.outputLog(message, date), date));
        mapper.addToMap(dm);
        return Logger.ERROR;
    }

    public void mapping() {
        mapper.show();
    }
}
