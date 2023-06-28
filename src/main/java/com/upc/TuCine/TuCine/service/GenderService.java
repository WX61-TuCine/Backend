package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.GenderDto;
import com.upc.TuCine.TuCine.dto.save.Gender.GenderSaveDto;

import java.util.List;

public interface GenderService {

    List<GenderDto> getAllGenders();

    GenderDto createGender(GenderSaveDto genderSaveDto);

}
