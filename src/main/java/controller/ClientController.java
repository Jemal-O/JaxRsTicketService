package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import javax.xml.datatype.DatatypeConfigurationException;
import org.dom4j.DocumentException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import exception.IsPaidException;
import objects.DataTransfer;
import objects.Ticket;
import objects.TransferDataCreation;

@Controller
public class ClientController {
    private static final String URL = "http://localhost:8085/JaxRsTicketService/";
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/reserve")
    public ModelAndView reserveTicket(@RequestParam(name = "name") String name, @RequestParam String lastName,
            @RequestParam String patronymicName, @RequestParam String departCity, @RequestParam String arrivalCity,
            @RequestParam String departDate, @RequestParam String arrivalDate,
            @RequestParam(name = "birthDate") String birthDate, @RequestParam String departTime,
            @RequestParam String arrivalTime, ModelAndView modelAndView)
                    throws IOException, DatatypeConfigurationException, ParseException {

        DataTransfer transfer = (new TransferDataCreation()).initDataTransfer(name, lastName, patronymicName,
                departCity, arrivalCity, departDate, departTime, arrivalDate, arrivalTime, birthDate);
        String ticketNumber = restTemplate.postForObject(URL + "reserve", transfer, String.class);
        modelAndView.getModelMap().addAttribute("ticket", ticketNumber);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/getTicket")
    private ModelAndView getUser(@RequestParam(value = "ticketNum") int ticketNum, ModelAndView modelAndView) {
        try {
            Ticket ticket = restTemplate.getForObject(URL + "getTicket/" + ticketNum, Ticket.class);
            addAttributeToModel(modelAndView, ticket);
        } catch (HttpServerErrorException ex) {
            modelAndView.getModelMap().addAttribute("mistake", ticketNum);
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/pay")
    public ModelAndView payTicket(@RequestParam(value = "ticketNum") String ticketNum, ModelAndView modelAndView)
            throws IOException, DocumentException {
        try {
            ResponseEntity<Ticket> result = restTemplate.exchange(URL + "pay/" + ticketNum, HttpMethod.PUT, getEntity(),
                    Ticket.class);
            addAttributeToModel(modelAndView, result.getBody());
        } catch (IsPaidException ex) {
            modelAndView.getModelMap().addAttribute("mistake", ticketNum);
        } catch (HttpServerErrorException ex) {
            modelAndView.getModelMap().addAttribute("mistake", ticketNum);
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/return")
    public ModelAndView returnTicket (@RequestParam Integer ticketNum,
            ModelAndView modelAndView) throws IOException {
        ResponseEntity<String> result = restTemplate.exchange(URL + "return/" + ticketNum, HttpMethod.DELETE, getEntity(),
                String.class);
        modelAndView.getModelMap().addAttribute("cancelTicket", result.getBody());
        modelAndView.getModelMap().addAttribute("ticketId", ticketNum);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    private HttpEntity<Integer> getEntity () {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity<Integer> entity = new HttpEntity<Integer>(headers);
        return entity;
    }

    private void addAttributeToModel(ModelAndView modelAndView, Ticket ticket) {
        modelAndView.getModelMap().addAttribute("ticketNum", ticket.getTicketNum());
        modelAndView.getModelMap().addAttribute("departDate", ticket.getDepartDateTime());
        modelAndView.getModelMap().addAttribute("departCity", ticket.getDepartCity());
        modelAndView.getModelMap().addAttribute("arrivalDate", ticket.getArrivalDateTime());
        modelAndView.getModelMap().addAttribute("arrivalCity", ticket.getArrivalCity());
        modelAndView.getModelMap().addAttribute("status", ticket.getTicketStatus().name());
        modelAndView.setViewName("index");
    }
    
    

}