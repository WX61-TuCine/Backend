package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.ActorDto;
import com.upc.TuCine.TuCine.dto.save.Actor.ActorSaveDto;
import com.upc.TuCine.TuCine.service.ActorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Actor", description = "API de Actores")
@RequestMapping ("/api/TuCine/v1")
public class ActorController {

    @Autowired
    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/actors")
    public ResponseEntity<List<ActorDto>> getAllActors() {
        return new ResponseEntity<List<ActorDto>>(actorService.getAllActors(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/actors
    //Method: POST
    @Transactional
    @PostMapping("/actors")
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorSaveDto actorSaveDto){
        return new ResponseEntity<ActorDto>(actorService.createActor(actorSaveDto), HttpStatus.CREATED);
    }

}
