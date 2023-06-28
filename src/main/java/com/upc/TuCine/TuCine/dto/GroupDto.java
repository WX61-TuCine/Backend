package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class GroupDto {
    private Integer id;
    private String name;
    private String ubication;
    private String description;
    private List<Person> persons;
}
