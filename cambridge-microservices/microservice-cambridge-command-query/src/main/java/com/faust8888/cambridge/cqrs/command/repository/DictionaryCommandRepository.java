package com.faust8888.cambridge.cqrs.command.repository;

import com.faust8888.cambridge.cqrs.command.model.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryCommandRepository extends CrudRepository<Dictionary, Long> {
}
