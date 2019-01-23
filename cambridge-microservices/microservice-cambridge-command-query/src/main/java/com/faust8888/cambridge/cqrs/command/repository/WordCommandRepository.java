package com.faust8888.cambridge.cqrs.command.repository;

import com.faust8888.cambridge.cqrs.command.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCommandRepository extends CrudRepository<Word, Long> {
}
