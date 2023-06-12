package com.buttermove.controller;

import com.buttermove.dto.ResultSalesDTO;
import com.buttermove.dto.SalesDTO;
import com.buttermove.dto.StatesDTO;
import com.buttermove.errors.EmptyDTOException;
import com.buttermove.errors.UnsupportedStatesException;
import com.buttermove.model.StateModel;
import com.buttermove.service.StatesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Este es el que realiza o ejecta las peticiones REST
@RestController
public class StateController {
   @Autowired
   private StatesServices statesServices;//Se otiene el servcio de estados que se encuentra en el contexto
    @RequestMapping(value = "/getAllStates",method = RequestMethod.GET,produces = "application/json")
    public List<StatesDTO> getAllStatesModel(){
        List<StatesDTO> states=statesServices.getAllStates();
        return states;
    }
    @RequestMapping(value = "/getStateById",method = RequestMethod.GET,produces = "application/json")
    public StateModel getStateById(@RequestBody StatesDTO statesDTO) throws UnsupportedStatesException {
        StatesDTO state= statesServices.getStateById(statesDTO);
        return state;
    }
    //Serviciio RESt que ejecuta el calculo
    @RequestMapping(value = "/calcAmount",method = RequestMethod.POST,produces = "application/json")
    public ResultSalesDTO calcAmount(@Valid @RequestBody SalesDTO statesDTO) throws UnsupportedStatesException, EmptyDTOException, IllegalAccessException {
        ResultSalesDTO resultSalesDTO= statesServices.calcAmount(statesDTO);//Se ejecuta servicio que realiza el calculo
        return resultSalesDTO;
    }
}
