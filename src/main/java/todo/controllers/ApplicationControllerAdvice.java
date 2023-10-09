package todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import todo.exceptions.ApiErrors;
import todo.exceptions.BusinessRuleException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BusinessRuleException exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleNullPointerExceptionException(NullPointerException ex) {
        String errorMessage = ex.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
        String message = "Campos obrigatórios não informados!";
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ApiErrors(message);
    }

}
