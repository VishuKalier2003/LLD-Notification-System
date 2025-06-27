package logging.logger.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import logging.logger.domain.model.DataModel;

public interface DataRepo extends MongoRepository<DataModel, String> {    
}
