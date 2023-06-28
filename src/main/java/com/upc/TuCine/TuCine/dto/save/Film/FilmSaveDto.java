package com.upc.TuCine.TuCine.dto.save.Film;

import com.upc.TuCine.TuCine.model.ContentRating;
import lombok.Data;

@Data
public class FilmSaveDto {
    private Integer id;
    private String title;
    private Integer year;
    private String synopsis;
    private String poster;
    private String trailer;
    private Integer duration;
    private FilmContentRatingSaveDto contentRating;
}