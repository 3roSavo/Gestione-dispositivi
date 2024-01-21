package savogineros.Gestionedispositivi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    // Indico che questo metodo gestirà le eccezioni di tipo BadRequestException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public String handleBadRequest(BadRequestException exception) {
        return exception.getMessage();
    }

}
