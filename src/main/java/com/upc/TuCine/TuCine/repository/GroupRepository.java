package com.upc.TuCine.TuCine.repository;

import com.upc.TuCine.TuCine.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    boolean existsByName(String name);
    List<Group> findAllByPerson_id(Integer person_id);

}
