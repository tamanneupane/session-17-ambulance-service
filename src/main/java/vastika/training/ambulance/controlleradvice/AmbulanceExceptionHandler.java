package vastika.training.ambulance.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vastika.training.ambulance.dto.ErrorResponse;
import vastika.training.ambulance.exceptions.AmbulanceNotFoundException;
import vastika.training.ambulance.utils.Constants;

@ControllerAdvice
public class AmbulanceExceptionHandler {

    @ExceptionHandler(AmbulanceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAmbulanceNotFoundException(AmbulanceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(Constants.StatusCode.AMBULANCE_NOT_FOUND,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception){
        ErrorResponse errorResponse = new ErrorResponse(Constants.StatusCode.MISSING_PARAMETER, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception){
//        ErrorResponse errorResponse = new ErrorResponse(Constants.StatusCode.GENERIC_ERROR,"GENERIC_ERROR");
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
