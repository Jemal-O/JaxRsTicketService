package objects;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// объект никуда не передается. В чем смысл этих аннотаций
public class TicketStorage implements Storage {
    private Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    public Ticket getTicket(int ticketId) {
        return tickets.get(ticketId);
    }

    public void setTicket(Ticket ticket) {
        tickets.put(ticket.getTicketNum(), ticket);
    }

    public void removeTicket(int ticketId) {
        tickets.remove(ticketId);
    }

}
