package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.dto.TicketDto;
import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.TypeUser.TypeUserSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Ticket;
import com.upc.TuCine.TuCine.model.TypeUser;
import com.upc.TuCine.TuCine.repository.TypeUserRepository;
import com.upc.TuCine.TuCine.service.TypeUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TypeUserServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private TypeUserDto EntityToDto(TypeUser typeUser){
        return modelMapper.map(typeUser, TypeUserDto.class);
    }

    private TypeUser DtoToEntity(TypeUserDto typeUserDto){
        return modelMapper.map(typeUserDto, TypeUser.class);
    }
    @Override
    public List<TypeUserDto> getAllTypeUsers() {
        List<TypeUser> typeUsers = typeUserRepository.findAll();
        return typeUsers.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TypeUserDto createTypeUser(TypeUserSaveDto typeUserSaveDto) {

        TypeUserDto typeUserDto = modelMapper.map(typeUserSaveDto, TypeUserDto.class);

        validateTypeUser(typeUserDto);
        existsTypeUserByName(typeUserDto.getName());
        TypeUser typeUser = DtoToEntity(typeUserDto);
        TypeUser createdTypeUser = typeUserRepository.save(typeUser);
        return EntityToDto(createdTypeUser);
    }

    @Override
    public TypeUserDto updateTypeUser(Integer id, TypeUserSaveDto typeUserSaveDto) {
        TypeUser typeUser = typeUserRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El tipo de usuario no existe"));
        TypeUserDto typeUser1 = EntityToDto(typeUser);
        validateTypeUser(typeUser1);
        typeUser.setName(typeUserSaveDto.getName());
        return EntityToDto(typeUserRepository.save(typeUser));
    }

    @Override
    public String deleteTypeUser(Integer id) {
        TypeUser typeUser = typeUserRepository.findById(id)
                .orElseThrow(() -> new ValidationException("El tipo de usuario no existe"));
        typeUserRepository.delete(typeUser);
        return "El tipo de usuario ha sido eliminado";
    }

    void validateTypeUser(TypeUserDto typeUser) {
        if (typeUser.getName() == null || typeUser.getName().isEmpty()) {
            throw new ValidationException("El nombre del tipo de usuario no puede estar vacío");
        }
    }

    void existsTypeUserByName(String name) {
        if (typeUserRepository.existsTypeUserByName(name)) {
            throw new ValidationException("El tipo de usuario ya existe con este nombre");
        }
    }
}
