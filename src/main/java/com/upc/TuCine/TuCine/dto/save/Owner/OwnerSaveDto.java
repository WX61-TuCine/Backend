package com.upc.TuCine.TuCine.dto.save.Owner;

import lombok.Data;

@Data
public class OwnerSaveDto {
    private String bankAccount;
    private OwnerPersonSaveDto person;
}
