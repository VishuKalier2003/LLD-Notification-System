package logging.logger.service.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.logger.domain.model.DataModel;
import logging.logger.domain.model.LogModel;
import logging.logger.repo.DataRepo;
import logging.logger.repo.LogRepo;

@Service
public class SearchService {
    
    @Autowired
    private DataRepo dataRepo;

    @Autowired
    private LogRepo logRepo;

    public List<DataModel> searchInDataSheet() {
        return dataRepo.findAll();
    }

    public List<LogModel> searchInLogModel() {
        return logRepo.findAll();
    }

    public LogModel searchInLogModel(String uuid) {
        Optional<LogModel> lm = logRepo.findById(uuid);
        return lm.isEmpty() ? null : lm.get();
    }

    public DataModel searchInDataModel(String uuid) {
        Optional<DataModel> dm = dataRepo.findById(uuid);
        return dm.isEmpty() ? null : dm.get();
    }
}
