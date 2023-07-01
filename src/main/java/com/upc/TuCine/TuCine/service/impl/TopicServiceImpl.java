package com.upc.TuCine.TuCine.service.impl;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.TopicDto;
import com.upc.TuCine.TuCine.dto.save.Topic.TopicSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.model.Group;
import com.upc.TuCine.TuCine.model.Topic;
import com.upc.TuCine.TuCine.repository.TopicRepository;
import com.upc.TuCine.TuCine.service.TopicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TopicServiceImpl() {
        this.modelMapper = new ModelMapper();
    }

    private TopicDto EntityToDto(Topic topic){
        return modelMapper.map(topic, TopicDto.class);
    }

    private Topic DtoToEntity(TopicDto topicDto){
        return modelMapper.map(topicDto, Topic.class);
    }

    @Override
    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDto createTopic(TopicSaveDto topicSaveDto) {


        TopicDto topicDto = modelMapper.map(topicSaveDto, TopicDto.class);

        topicValidate(topicDto);
        existsTopicByName(topicDto.getName());

        Topic topic = DtoToEntity(topicDto);
        return EntityToDto(topicRepository.save(topic));
    }

    @Override
    public TopicDto updateTopic(Integer id, TopicSaveDto topicSaveDto) {
        TopicDto topicDto = modelMapper.map(topicSaveDto, TopicDto.class);
        Topic topic = DtoToEntity(topicDto);
        Topic topicUpdate = topicRepository.findById(id).orElseThrow(() -> new ValidationException("No existe el tema"));
        topicUpdate.setName(topic.getName());
        return EntityToDto(topicRepository.save(topicUpdate));
    }

    @Override
    public String deleteTopic(Integer id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new ValidationException("No existe el tema"));
        topicRepository.delete(topic);
        return "Tema " + topic.getName() + " eliminado";
    }

    private void topicValidate(TopicDto topicDto) {
        if (topicDto.getName() == null || topicDto.getName().isEmpty()) {
            throw new ValidationException("El nombre del tema no puede estar vacío");
        }
    }

    private void existsTopicByName(String name) {
        if (topicRepository.existsByName(name)) {
            throw new ValidationException("El tema ya existe");
        }
    }
}
