package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Group;
import com.upc.TuCine.TuCine.repository.GroupRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import com.upc.TuCine.TuCine.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    GroupServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private GroupDto EntityToDto(Group group){
        return modelMapper.map(group, GroupDto.class);
    }

    private Group DtoToEntity(GroupDto groupDto){
        return modelMapper.map(groupDto,Group.class);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<Group> groups= groupRepository.findAll();
        return groups.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto createGroup(GroupSaveDto groupSaveDto) {

        GroupDto groupDto = modelMapper.map(groupSaveDto, GroupDto.class);

        groupValidate(groupDto);
        existsGroupByName(groupDto.getName());

        Group group = DtoToEntity(groupDto);
        return EntityToDto(groupRepository.save(group));

    }

    @Override
    public List<PersonDto> getPersonsByGroupId(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró el grupo con el ID: " + id));

        return group.getPersons().stream()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }


    private void groupValidate(GroupDto group){
        if(group.getName()==null || group.getName().isEmpty()){
            throw new ValidationException("El nombre es obligatorio");
        }
        if(group.getDescription()==null || group.getDescription().isEmpty()){
            throw new ValidationException("La descripcion es obligatoria");
        }
        if(group.getUbication()==null || group.getUbication().isEmpty()){
            throw new ValidationException("La ubicacion es obligatoria");
        }
    }

    private void existsGroupByName(String name){
        if(groupRepository.existsByName(name)){
            throw new ValidationException("El nombre de este grupo ya existe");
        }
    }
}
