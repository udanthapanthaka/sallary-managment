package lk.ijse.backend.adviser;

import lk.ijse.backend.util.ResponseUtil;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseUtil exceptionHandler(Exception ex){
        return new ResponseUtil(500,"Internal Server Error",ex.getMessage());

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseUtil(400, "Validation Error: " + errors, null);
    }

}
