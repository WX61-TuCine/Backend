package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    GroupDto createGroup(GroupSaveDto groupSaveDto);

    GroupDto getGroupById(Integer id);

    void addTopicToGroup(Integer groupId, Integer topicId);

    String deleteGroup(Integer id);

    PersonDto getPersonByGroupId(Integer id);

}
