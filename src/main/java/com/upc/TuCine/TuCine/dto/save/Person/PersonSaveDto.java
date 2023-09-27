package com.upc.TuCine.TuCine.dto.save.Person;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonSaveDto {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phone;
    private String photo;
    private String email;
    private String password;
    private String numberDni;
    private PersonGenderSaveDto gender;
    private PersonTypeUserSaveDto typeUser;
}
