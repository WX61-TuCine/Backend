package com.upc.TuCine.TuCine.dto.save.Ticket;

import com.upc.TuCine.TuCine.model.Customer;
import com.upc.TuCine.TuCine.model.Showtime;
import lombok.Data;

@Data
public class TicketSaveDto {
    private Integer numberSeats;
    private Float totalPrice;
    private TicketCustomerSaveDto customer;
    private TicketShowtimeSaveDto showtime;
}
