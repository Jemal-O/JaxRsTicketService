package objects;

import java.util.Date;

public class TicketCreation {

    private static final float DEFAULT_PRICE = 1000;
    private int lastTicketNum = 1;

    public Ticket initTicket(Person person, String departCity, String arrivalCity, Date departDateTime, Date arrivalDateTime,
            Date birthDate) {
        Ticket ticket = new Ticket();
        ticket.setTicketNum(lastTicketNum++);
        ticket.setPerson(person);
        ticket.setDepartCity(departCity);
        ticket.setArrivalCity(arrivalCity);
        ticket.setDepartDateTime(departDateTime);
        ticket.setArrivalDateTime(arrivalDateTime);
        ticket.setTicketStatus(TicketStatus.RESERVE);
        ticket.setPrice(DEFAULT_PRICE);
        return ticket;
    }

}
