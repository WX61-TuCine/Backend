package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.OwnerDto;
import com.upc.TuCine.TuCine.dto.save.Owner.OwnerSaveDto;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> getAllOwners();

    OwnerDto createOwner(OwnerSaveDto ownerSaveDto);
}
