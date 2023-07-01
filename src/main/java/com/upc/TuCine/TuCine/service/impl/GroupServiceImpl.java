package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ContentRatingDto;
import com.upc.TuCine.TuCine.dto.FilmDto;
import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;
import com.upc.TuCine.TuCine.exception.ResourceNotFoundException;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.*;
import com.upc.TuCine.TuCine.repository.GroupRepository;
import com.upc.TuCine.TuCine.repository.PersonRepository;
import com.upc.TuCine.TuCine.repository.TopicRepository;
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
    private TopicRepository topicRepository;

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

        Person person = personRepository.findById(groupDto.getPerson().getId())
                .orElseThrow(() -> new ValidationException("No se encontró la persona con el ID: " + groupDto.getPerson().getId()));

        groupDto.setPerson(person);

        Group group = DtoToEntity(groupDto);
        return EntityToDto(groupRepository.save(group));

    }

    @Override
    public GroupDto getGroupById(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró el grupo con el ID: " + id));

        return EntityToDto(group);
    }


    @Override
    public PersonDto getPersonByGroupId(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró el grupo con el ID: " + id));

        Person person = group.getPerson();
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public void addTopicToGroup(Integer idGroup, Integer idTopic){
        Group group = groupRepository.findById(idGroup).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el grupo con id: " + idGroup));
        Topic topic = topicRepository.findById(idTopic).orElseThrow(() -> new ResourceNotFoundException("No se encuentra el tema con id: " + idTopic));

        group.getTopics().add(topic);
        groupRepository.save(group);
    }

    @Override
    public String deleteGroup(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró el grupo con el ID: " + id));

        groupRepository.delete(group);
        return "Se eliminó el grupo con el ID: " + id;
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
        if(group.getPerson()==null){
            throw new ValidationException("La persona creadora del grupo es obligatoria");
        }
    }

    private void existsGroupByName(String name){
        if(groupRepository.existsByName(name)){
            throw new ValidationException("El nombre de este grupo ya existe");
        }
    }
}
