package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;
import com.upc.TuCine.TuCine.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Groups", description = "API de Groups")
@RequestMapping("/api/TuCine/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups")
    @Operation(summary = "Obtener todos los grupos")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la lista de grupos"
    )
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/groups")
    @Operation(summary = "Crear un grupo")
    @ApiResponse(
            responseCode = "201",
            description = "Se creó el grupo"
    )
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupSaveDto groupSaveDto) {
        GroupDto createdGroupDto = groupService.createGroup(groupSaveDto);
        return new ResponseEntity<>(createdGroupDto, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups/{id}/persons")
    @Operation(summary = "Obtener las personas por ID de grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvieron las personas del grupo"
    )
    public ResponseEntity<List<PersonDto>> getPersonsByGroupId(@PathVariable("id") Integer id) {
        List<PersonDto> personDtoList = groupService.getPersonsByGroupId(id);
        return new ResponseEntity<>(personDtoList, HttpStatus.OK);
    }

}
