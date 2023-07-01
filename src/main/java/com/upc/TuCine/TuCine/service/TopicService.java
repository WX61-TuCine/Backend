package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.TopicDto;
import com.upc.TuCine.TuCine.dto.save.Topic.TopicSaveDto;

import java.util.List;

public interface TopicService {

    List<TopicDto> getAllTopics();

    TopicDto createTopic(TopicSaveDto topicSaveDto);

    TopicDto updateTopic(Integer id, TopicSaveDto topicSaveDto);

    String deleteTopic(Integer id);
}
