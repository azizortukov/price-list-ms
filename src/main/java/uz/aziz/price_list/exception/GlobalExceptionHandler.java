package uz.aziz.price_list.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import uz.aziz.price_list.model.dto.ResponseDto;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseDto<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = "Failed to convert value: '" + e.getValue() + "' to required type: '" + Objects.requireNonNull(e.getRequiredType()) + "'";
        return new ResponseDto<>(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder msg = new StringBuilder("Check field(s) format: ");
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            msg.append(fieldName).append(" - ").append(errorMessage);
        });
        return new ResponseDto<>(msg.toString());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseDto<?> handleNoResourceFoundException(NoResourceFoundException e) {
        return new ResponseDto<>("Page not found");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseDto<?> handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        return new ResponseDto<>(throwable.getMessage());
    }

}
