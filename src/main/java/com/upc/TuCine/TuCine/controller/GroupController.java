package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.GroupDto;
import com.upc.TuCine.TuCine.dto.PersonDto;
import com.upc.TuCine.TuCine.dto.save.Group.GroupSaveDto;
import com.upc.TuCine.TuCine.service.GroupService;
import com.upc.TuCine.TuCine.service.PersonService;
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

    private final GroupService groupService;
    private final PersonService personService;

    @Autowired
    public GroupController(GroupService groupService,
                           PersonService personService) {
        this.groupService = groupService;
        this.personService = personService;
    }


    @Transactional(readOnly = true)
    @GetMapping("/groups")
    @Operation(summary = "Obtener todos los grupos")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la lista de grupos"
    )
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups/{id}")
    @Operation(summary = "Obtener un grupo por su ID")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo el grupo"
    )
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Integer id) {
        GroupDto group = groupService.getGroupById(id);
        if(group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/groups")
    @Operation(summary = "Crear un grupo")
    @ApiResponse(
            responseCode = "201",
            description = "Se creó el grupo"
    )
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupSaveDto groupSaveDto) {
        GroupDto createdGroup = groupService.createGroup(groupSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @Transactional(readOnly = true)
    @GetMapping("/groups/{id}/person")
    @Operation(summary = "Obtener la persona creadora de un grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la persona creadora del grupo"
    )
    public ResponseEntity<PersonDto> getPersonByGroupId(@PathVariable Integer id) {
        PersonDto person = groupService.getPersonByGroupId(id);
        if(person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/groups/{groupId}/topics/{topicId}")
    @Operation(summary = "Agregar un tema a un grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se agregó el tema al grupo"
    )
    public ResponseEntity<String > addTopicToGroup(@PathVariable("groupId") Integer groupId, @PathVariable("topicId") Integer topicId) {
        groupService.addTopicToGroup(groupId, topicId);
        return ResponseEntity.ok("Se agregó el tema al grupo" );
    }

    @GetMapping("/persons/{personId}/groups")
    @Operation(summary = "Obtener todos los grupos de una persona")
    @ApiResponse(
            responseCode = "200",
            description = "Se obtuvo la lista de grupos de la persona"
    )
    public ResponseEntity<List<GroupDto>> getAllGroupsByPersonId(@PathVariable("personId") Integer personId) {
        List<GroupDto> groupsDtoList = personService.getAllGroupsByPersonId(personId);
        if (groupsDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(groupsDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    @Operation(summary = "Eliminar un grupo")
    @ApiResponse(
            responseCode = "200",
            description = "Se eliminó el grupo"
    )
    public ResponseEntity<String> deleteGroup(@PathVariable Integer id) {
        String message = groupService.deleteGroup(id);
        return ResponseEntity.ok(message);
    }
}
