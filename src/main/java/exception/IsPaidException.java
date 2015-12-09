package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class IsPaidException extends HttpServerErrorException {

    public IsPaidException(HttpStatus statusCode) {
        super(statusCode);
    }

    private static final long serialVersionUID = 1L;

}
