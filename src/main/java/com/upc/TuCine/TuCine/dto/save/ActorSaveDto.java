package com.upc.TuCine.TuCine.dto.save;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActorSaveDto {

    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthday;

}
