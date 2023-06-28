package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    boolean existsByName(String name);
}
