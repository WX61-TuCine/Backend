package com.upc.TuCine.TuCine.dto.save.Owner;

import com.upc.TuCine.TuCine.model.Person;
import lombok.Data;

@Data
public class OwnerSaveDto {
    private String bankAccount;
    private OwnerPersonSaveDto person;
}
