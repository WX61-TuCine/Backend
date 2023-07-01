package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.dto.TopicDto;
import com.upc.TuCine.TuCine.dto.save.Topic.TopicSaveDto;
import com.upc.TuCine.TuCine.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Topics", description = "API de Topics (temas de conversación o discusión)")
@RequestMapping("/api/TuCine/v1")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/topics")
    @Operation(summary = "Obtener toda la lista de topics")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo la lista de topics",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicDto.class,type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron los topics",
                    content = @Content
            )
    })
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topics = topicService.getAllTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/topics")
    @Operation(summary = "Crear un nuevo topic")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se creó el topic",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No se pudo crear el topic",
                    content = @Content
            )
    })
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicSaveDto topicSaveDto) {
        TopicDto createdTopic = topicService.createTopic(topicSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }

    @Transactional
    @PutMapping("/topics/{id}")
    @Operation(summary = "Actualizar un topic")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se actualizó el topic",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TopicDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró el topic",
                    content = @Content
            )
    })
    public ResponseEntity<TopicDto> updateTopic(@PathVariable Integer id, @RequestBody TopicSaveDto topicSaveDto) {
        TopicDto updatedTopic = topicService.updateTopic(id, topicSaveDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTopic);
    }

    @Transactional
    @DeleteMapping("/topics/{id}")
    @Operation(summary = "Eliminar un topic")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se eliminó el topic",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró el topic",
                    content = @Content
            )
    })
    public ResponseEntity<String> deleteTopic(@PathVariable Integer id) {
        String message = topicService.deleteTopic(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}

