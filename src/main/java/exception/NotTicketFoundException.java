package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class NotTicketFoundException extends HttpServerErrorException {

    public NotTicketFoundException(HttpStatus statusCode) {
        super(statusCode.NOT_FOUND);
    }

    private static final long serialVersionUID = 1L;

}
