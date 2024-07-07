package com.ansitindia.taskmgt.service;

import com.ansitindia.taskmgt.model.Counter;
import com.ansitindia.taskmgt.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private CounterRepository counterRepository;

    public Long generateSequence(String seqName) {
        // Get the counter object for the given collection name
        Counter counter = counterRepository.findByCollectionName(seqName);

        if (counter == null) {
            // If counter does not exist, create and initialize it
            counter = new Counter();
            counter.setId(seqName);
            counter.setCollectionName(seqName);
            counter.setSequence(1L); // Initial value for sequence
            counterRepository.save(counter);
        } else {
            // Increment the sequence value
            counter.setSequence(counter.getSequence() + 1);
            counterRepository.save(counter);
        }

        return counter.getSequence();
    }
}
