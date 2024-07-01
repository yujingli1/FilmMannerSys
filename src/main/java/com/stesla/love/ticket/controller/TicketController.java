package com.stesla.love.ticket.controller;

import com.stesla.love.ticket.domain.Ticket;
import com.stesla.love.ticket.service.TicketService;
import com.stesla.love.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public Map<String, Object> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResultUtil.success(tickets).getMap();
    }

    @GetMapping("/search")
    public Map<String, Object> getTicketsByMovieName(@RequestParam String movieName) {
        List<Ticket> tickets = ticketService.getTicketsByMovieName(movieName);
        return ResultUtil.success(tickets).getMap();
    }

    @GetMapping("/count")
    public Map<String, Object> getTicketCountByMovieAndTime(@RequestParam int movieId,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String startTime,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String endTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date startDate = dateFormat.parse(startTime);
        Date endDate = dateFormat.parse(endTime);

        int count = ticketService.getTicketCountByMovieAndTime(movieId, startDate, endDate);
        return ResultUtil.success(count).getMap();
    }

    @GetMapping("/movieName/{movieId}")
    public Map<String, Object> getMovieNameByMovieId(@PathVariable int movieId) {
        String movieName = ticketService.getMovieNameByMovieId(movieId);
        return ResultUtil.success(movieName).getMap();
    }

    @PostMapping
    public Map<String, Object> addTicket(@RequestBody Ticket ticket) {
        try {
            ticketService.addTicket(ticket);
            return ResultUtil.success().getMap();
        } catch (Exception e) {
            return ResultUtil.error(500, e.getMessage()).getMap();
        }
    }
}
