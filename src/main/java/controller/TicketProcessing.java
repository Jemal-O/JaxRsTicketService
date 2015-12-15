package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import exception.IsPaidException;
import exception.NotTicketFoundException;
import objects.DataTransfer;
import objects.Person;
import objects.Storage;
import objects.Ticket;
import objects.TicketCreation;
import objects.TicketStatus;
import objects.TicketStorage;

// кодировка только UTF-8!
@RestController
@RequestMapping(value = "/ticket", produces = "application/xml; charset=utf-8")
public class TicketProcessing {
    private Storage tickets = new TicketStorage();
    private TicketCreation ticketCreation = new TicketCreation();

    @RequestMapping(value = "/reserve", method = RequestMethod.POST, consumes = "application/xml")
    public String reserveTicket(@RequestBody DataTransfer dataTransfer) {
        Person person = new Person(dataTransfer.getName(), dataTransfer.getLastName(), dataTransfer.getPatronymicName(),
                dataTransfer.getBirthDate());
        Ticket ticket = ticketCreation.initTicket(person, dataTransfer.getDepartCity(), dataTransfer.getArrivalCity(),
                dataTransfer.getDepartDate(), dataTransfer.getArrivalDate(), dataTransfer.getBirthDate());
        tickets.setTicket(ticket);
        return Integer.toString(ticket.getTicketNum());
    }

    @RequestMapping(value = "/getTicket/{ticketNum}", method = RequestMethod.GET)
    public Ticket getTicket(@PathVariable int ticketNum) {
        if (tickets.getTicket(ticketNum) == null) {
            // 1. тут бы подошел кастомный эксепшен
            // 2. HttpStatus.CONFLICT странный выбор. Больше подошел бы
            // HttpStatus.NOT_FOUND
            throw new NotTicketFoundException(HttpStatus.NOT_FOUND);
        }
        return tickets.getTicket(ticketNum);
    }

    // /return - странный путь для операции DELETE
    // зачем возвращать строку. Особенно "true"/"false"
    @RequestMapping(value = "/return/{ticketNum}", method = RequestMethod.DELETE)
    public ResponseEntity<?> returnTicket(@PathVariable int ticketNum) {
        if (tickets.getTicket(ticketNum) != null) {
            tickets.removeTicket(ticketNum);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new NotTicketFoundException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/pay/{ticketNum}", method = RequestMethod.PUT)
    public Ticket payTicket(@PathVariable int ticketNum) {
        // каждый раз получаешь тикет из хранилища. А что если хранилище юудет
        // не HashMap а база?
        Ticket ticket = tickets.getTicket(ticketNum);
        if (ticket != null) {
            if (ticket.getTicketStatus() != TicketStatus.IS_PAID) {
                ticket.setTicketStatus(TicketStatus.IS_PAID);
            } else {
                // опять странный статус
                throw new IsPaidException(HttpStatus.NOT_FOUND);
            }
        } else {
            // и тут статус и исключение
            throw new NotTicketFoundException(HttpStatus.NOT_FOUND);
        }
        return tickets.getTicket(ticketNum);
    }
}
