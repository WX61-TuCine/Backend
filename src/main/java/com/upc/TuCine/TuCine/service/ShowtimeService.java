package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.ShowtimeDto;
import com.upc.TuCine.TuCine.dto.save.Showtime.ShowtimeSaveDto;
import com.upc.TuCine.TuCine.model.Showtime;

import java.util.List;

public interface ShowtimeService {

    List<ShowtimeDto>getAllShowtimes();

    ShowtimeDto getShowtimeById(Integer id);

    ShowtimeDto createShowtime(ShowtimeSaveDto showtimeSaveDto);

    ShowtimeDto updateShowtime(Integer id, ShowtimeSaveDto showtimeSaveDto);

    ShowtimeDto deleteShowtime(Integer id);


}
