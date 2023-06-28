package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    boolean existsByName(String name);
}
