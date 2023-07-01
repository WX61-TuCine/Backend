package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.dto.*;
import com.upc.TuCine.TuCine.dto.save.Film.FilmSaveDto;
import com.upc.TuCine.TuCine.exception.ValidationException;
import com.upc.TuCine.TuCine.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Film", description = "API de Film")
@RequestMapping("/api/TuCine/v1")
public class FilmController {

    @Autowired
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films")
    @Operation(summary = "Obtener todas las películas")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo la lista de películas",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FilmDto.class, type = "array")
                            )
                    }
            ),

    })
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        return new ResponseEntity<>(filmService.getAllFilms(), HttpStatus.OK);
    }


    //URL: http://localhost:8080/api/TuCine/v1/films/{filmId}
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{filmId}")
    @Operation(summary = "Obtener una película por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo la película",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FilmDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró la película",
                    content = @Content
            )
    })
    public ResponseEntity<FilmDto> getFilmById(@PathVariable("filmId") Integer filmId) {
        FilmDto filmDto = filmService.getFilmById(filmId);
        if (filmDto == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(filmDto, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: POST
    @Transactional
    @PostMapping("/films")
    @Operation(summary = "Crear una película")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se creó la película",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FilmSaveDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No se pudo crear la película",
                    content = @Content
            )
    })
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmSaveDto filmSaveDto){
        FilmDto createdFilmDto= filmService.createFilm(filmSaveDto);
        return new ResponseEntity<>(createdFilmDto, HttpStatus.CREATED);
    }

    //Get content rating by Film Id
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/contentRating
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/contentRating")
    @Operation(summary = "Obtener el content rating de una película por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo el content rating de la película",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ContentRatingDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró el content rating de la película",
                    content = @Content
            )
    })
    public ResponseEntity<ContentRatingDto> getContentRatingByFilmId(@PathVariable("id") Integer id) {
        ContentRatingDto contentRatingDto = filmService.getContentRatingByFilmId(id);
        if (contentRatingDto == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(contentRatingDto, HttpStatus.OK);
    }

    //Get All Categories By Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/categories
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/categories")
    @Operation(summary = "Obtener todas las categorias de una película por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todas las categorias de la película",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class,type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron las categorias",
                    content = @Content
            )
    })
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByFilmId(@PathVariable("id") Integer id) {
        List<CategoryDto> categoryDtoList = filmService.getAllCategoriesByFilmId(id);
        if (categoryDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    //Get all the Showtimes of a Film by Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/showtimes
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/showtimes")
    @Operation(summary = "Obtener todos los showtimes de una película por su id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se obtuvieron todos los showtimes de la película",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ShowtimeDto.class,type = "array")
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron los showtimes",
                            content = @Content
                    )
            }
    )

    public ResponseEntity<List<ShowtimeDto>> getAllShowtimesByFilmId(@PathVariable("id") Integer id) {
        List<ShowtimeDto> showtimeDtoList = filmService.getAllShowtimesByFilmId(id);
        if (showtimeDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(showtimeDtoList, HttpStatus.OK);
    }

    //Get All Actors By Film ID
    //URL: http://localhost:8080/api/TuCine/v1/films/{id}/actors
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films/{id}/actors")
    @Operation(summary = "Obtener todos los actores de una película mediante el Id de la película")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todos los actores de la película",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ActorDto.class,type = "array")
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron los actores",
                    content = @Content
            )
    })
    public ResponseEntity<List<ActorDto>> getAllActorsByFilmId(@PathVariable("id") Integer id) {
        List<ActorDto> actorDtoList = filmService.getAllActorsByFilmId(id);
        if (actorDtoList == null) {
            return ResponseEntity.notFound().build(); // Manejar casos en los que no se encuentre el film
        }
        return new ResponseEntity<>(actorDtoList, HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films/{idFilm}/actors/{idActor}
    //Method: POST
    @Transactional
    @PostMapping("/films/{idFilm}/actors/{idActor}")
    @Operation(summary = "Añadir un actor a una película mediante el id de la película y el id del actor")
    public ResponseEntity<String> addActorToFilm(@PathVariable(value = "idFilm") Integer idFilm, @PathVariable(value = "idActor") Integer idActor){
        filmService.addActorToFilm(idFilm, idActor);
        return ResponseEntity.ok("Actor added to film successfully.");
    }

    // URL: http://localhost:8080/api/TuCine/v1/films/{filmId}/categories/{categoryId}
    // Method: POST
    @Transactional
    @PostMapping("/films/{filmId}/categories/{categoryId}")
    @Operation(summary = "Añadir una categoría a la película mediante el id de la película y el id de la categoría")
    public ResponseEntity<String> addCategoryToFilm(@PathVariable("filmId") Integer filmId, @PathVariable("categoryId") Integer categoryId) {
        filmService.addCategoryToFilm(filmId, categoryId);
        return ResponseEntity.ok("Category added to film successfully.");
    }




}
