package com.stesla.love.ticket.service;
import com.stesla.love.ticket.domain.Ticket;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();

    List<Ticket> getTicketsByMovieName(String movieName);

    int getTicketCountByMovieAndTime(int movieId, Date startTime, Date endTime);

    String getMovieNameByMovieId(int movieId);

    void addTicket(Ticket ticket) throws Exception;
}
