package objects;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ticket")
@XmlAccessorType(XmlAccessType.NONE)
public class Ticket {
    @XmlElement
    private int ticketNum;
    @XmlElement
    private String departCity;
    @XmlElement
    private String arrivalCity;
    @XmlElement
    private Date departDateTime;
    @XmlElement
    private Date arrivalDateTime;
    @XmlElement
    private float price;
    @XmlElement
    private TicketStatus ticketStatus;
    @XmlElement
    private Person person;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDepartDateTime() {
        return departDateTime;
    }

    public void setDepartDateTime(Date departDateTime) {
        this.departDateTime = departDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String toString() {
        return "ticket id - " + ticketNum
                + ", " + departCity
                + " - " + arrivalCity
                + ", departure time - " + departDateTime
                + ", destination time - " + arrivalDateTime
                + ", passenger - " + person
                + ", price - " + price
                + ", status - " + ticketStatus;
    }
    
}
