package logging.logger.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import logging.logger.domain.model.LogModel;

public interface LogRepo extends MongoRepository<LogModel, String> {
}
