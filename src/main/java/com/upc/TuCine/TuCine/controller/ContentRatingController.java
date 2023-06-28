package com.upc.TuCine.TuCine.controller;


import com.upc.TuCine.TuCine.dto.ContentRatingDto;
import com.upc.TuCine.TuCine.dto.save.ContentRating.ContentRatingSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.ContentRatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "ContentRating", description = "API de ContentRating")
@RequestMapping("/api/TuCine/v1")

public class ContentRatingController {

    @Autowired
    private ContentRatingService contentRatingService;

    public ContentRatingController(ContentRatingService contentRatingService) {
        this.contentRatingService = contentRatingService;
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/contentRatings")
    public ResponseEntity<List<ContentRatingDto>> getAllContentRatings() {
        return new ResponseEntity<>(contentRatingService.getAllContentRatings(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings/{id}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/contentRatings/{id}")
    public ResponseEntity<ContentRatingDto> getContentRatingById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ContentRatingDto>(contentRatingService.getContentRatingById(id), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/contentRatings
    //Method: POST
    @Transactional
    @PostMapping("/contentRatings")
    public ResponseEntity<ContentRatingDto> createContentRating(@RequestBody ContentRatingSaveDto contentRatingSaveDto){
        return new ResponseEntity<ContentRatingDto>(contentRatingService.createContentRating(contentRatingSaveDto), HttpStatus.CREATED);
    }
    

}
