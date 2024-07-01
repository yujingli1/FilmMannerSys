package com.stesla.love.ticket.service.impl;
import com.stesla.love.ticket.domain.Ticket;
import com.stesla.love.ticket.mapper.TicketMapper;
import com.stesla.love.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    @Override
    public List<Ticket> getTicketsByMovieName(String movieName) {
        return ticketMapper.getTicketsByMovieName(movieName);
    }

    @Override
    public int getTicketCountByMovieAndTime(int movieId, Date startTime, Date endTime) {
        return ticketMapper.getTicketCountByMovieAndTime(movieId, startTime, endTime);
    }

    @Override
    public String getMovieNameByMovieId(int movieId) {
        return ticketMapper.getMovieNameByMovieId(movieId);
    }

    @Override
    public void addTicket(Ticket ticket) throws Exception{
        int hallSize=ticketMapper.getHallSize(ticket.getHallId());
        int currentTicketCount=ticketMapper.getTicketCountByHallAndTime(ticket.getHallId(),ticket.getShowTime());
        if (currentTicketCount>=hallSize){
            throw new Exception("此场次已满，请购买其他场次的票");
        }
        ticketMapper.insertTicket(ticket);
    }
}
