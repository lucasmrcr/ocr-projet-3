package fr.chatop.api.exception;

import fr.chatop.api.dto.response.exception.ResponseExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResponseEntityException.class})
    public ResponseEntity<ResponseExceptionDTO> exception(ResponseEntityException exception, WebRequest req) {
        // When exception is caught, we return a response with the exception message and status
        return new ResponseEntity<>(
            new ResponseExceptionDTO(exception.getMessage()),
            exception.getStatus()
        );
    }

}
