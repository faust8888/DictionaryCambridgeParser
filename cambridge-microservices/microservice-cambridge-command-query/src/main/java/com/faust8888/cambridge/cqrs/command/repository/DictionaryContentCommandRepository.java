package com.faust8888.cambridge.cqrs.command.repository;

import com.faust8888.cambridge.cqrs.command.model.DictionaryContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryContentCommandRepository extends CrudRepository<DictionaryContent, Long> {
}
