package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import exception.IsPaidException;
import objects.DataTransfer;
import objects.Person;
import objects.Storage;
import objects.Ticket;
import objects.TicketCreation;
import objects.TicketStatus;
import objects.TicketStorage;

@RestController
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

    @RequestMapping(value = "/getTicket/{ticketNum}", method = RequestMethod.GET, produces = "application/xml")
    public Ticket getTicket(@PathVariable int ticketNum){
        if (tickets.getTicket(ticketNum) == null) {
            throw new HttpServerErrorException(HttpStatus.CONFLICT);
        }
        return tickets.getTicket(ticketNum);
    }

    @RequestMapping(value = "/return/{ticketNum}", method = RequestMethod.DELETE, produces = "application/xml")
    public String returnTicket(@PathVariable int ticketNum) {
        if (tickets.getTicket(ticketNum) != null) {
            tickets.removeTicket(ticketNum);
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping(value = "/pay/{ticketNum}", method = RequestMethod.PUT, produces = "application/xml")
    public Ticket payTicket(@PathVariable int  ticketNum) {
        if (tickets.getTicket(ticketNum) != null) {
            if (tickets.getTicket(ticketNum).getTicketStatus() != TicketStatus.IS_PAID) {
                tickets.getTicket(ticketNum).setTicketStatus(TicketStatus.IS_PAID);
            } else {
                throw new IsPaidException(HttpStatus.CONFLICT);
            }
        } else {
            throw new HttpServerErrorException(HttpStatus.CONFLICT);
        }
        return tickets.getTicket(ticketNum);
    }
}
