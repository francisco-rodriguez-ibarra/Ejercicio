package com.buttermove.errors;

import com.buttermove.dto.ErrorDTO;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

//Se preparan handlers exception custom para su manejo
@ControllerAdvice(annotations = RestController.class)
public class StateException {

    @ExceptionHandler(UnsupportedStatesException.class)//Usada para cuando sea un estado que no exista
    @ResponseBody
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleException(UnsupportedStatesException e){
        ErrorDTO result=new ErrorDTO(e.getMessage());
        return result;
    }
    @ExceptionHandler(EmptyDTOException.class)//usada cuando el objeto de entrada venga vacio
    @ResponseBody
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleException(EmptyDTOException e){
        ErrorDTO result=new ErrorDTO(e.getMessage());
        return result;

    }
    @ExceptionHandler(JsonParseException.class)//Se usa para validad que el json no venga mal formado
    @ResponseBody
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleException(JsonParseException e){
        ErrorDTO result=new ErrorDTO("JSON parse error");
        return result;
    }
    @ExceptionHandler(HeaderException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)//Se usar para exececion que tienen que ver con validaciones del header
    public ErrorDTO handleException(HeaderException e){
        ErrorDTO result=new ErrorDTO(e.getMessage());
        return  result;
    }
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(HttpMessageNotReadableException e){
        return new ErrorDTO("Required request body is missing or JSON parse error");

    }

}
