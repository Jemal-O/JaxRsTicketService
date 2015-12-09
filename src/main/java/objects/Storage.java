package objects;

public interface Storage {
    
    void setTicket(Ticket ticket);

    Ticket getTicket(int ticketNum);

    void removeTicket(int ticketNum);

}
