package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.Person.PersonSaveDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAllPersons();

    TypeUserDto getTypeUserByPersonId(Integer id);

    PersonDto createPerson(PersonSaveDto personSaveDto);
}
