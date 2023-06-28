package com.upc.TuCine.TuCine.dto.save.Group;

import lombok.Data;

@Data
public class GroupSaveDto {
    private String name;
    private String ubication;
    private String description;
    private GroupPersonSaveDto person;
}
