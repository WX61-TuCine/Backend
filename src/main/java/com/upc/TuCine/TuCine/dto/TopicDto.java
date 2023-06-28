package com.upc.TuCine.TuCine.dto;

import com.upc.TuCine.TuCine.model.Group;
import lombok.Data;

import java.util.List;

@Data
public class TopicDto {
    private Integer id;
    private String name;
    private List<Group> groups;
}
