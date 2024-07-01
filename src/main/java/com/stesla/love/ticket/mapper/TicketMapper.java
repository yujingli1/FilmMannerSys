package com.stesla.love.ticket.mapper;
import com.stesla.love.ticket.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Mapper
public interface TicketMapper {
    List<Ticket> getAllTickets();

    List<Ticket> getTicketsByMovieName(@Param("movieName") String movieName);

    int getTicketCountByMovieAndTime(@Param("movieId") int movieId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    String getMovieNameByMovieId(@Param("movieId") int movieId);
    int getHallSize(@Param("hallId")int hallId);
    int getTicketCountByHallAndTime(@Param("hallId")int hallId, @Param("showTime")Date showTime);
    void insertTicket(Ticket ticket);
}
