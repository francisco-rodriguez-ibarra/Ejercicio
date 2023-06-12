package com.buttermove.service;

import com.buttermove.Calculation.CalculationAmount;
import com.buttermove.dto.ResultSalesDTO;
import com.buttermove.dto.SalesDTO;
import com.buttermove.dto.StatesDTO;
import com.buttermove.errors.EmptyDTOException;
import com.buttermove.errors.UnsupportedStatesException;
import com.buttermove.factory.StatesFactory;
import com.buttermove.model.StateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//Servicio usado para realizar los calculos
@Service
public class StatesServicesImp implements StatesServices{
    private ModelMapper modelMap=  new ModelMapper();
    //Se accesa al bean del factory que se encuentra en el contexto
    private static StatesFactory statesFactory;
    public StatesServicesImp(StatesFactory states){
        this.statesFactory=states;
    }

    ////Se accesa al bean del calculationAmount que se encuentra en el contexto, el cual este contiene los metodo para el calculo
    @Autowired
    private CalculationAmount calculationAmount;
    @Override
    public List<StatesDTO> getAllStates() {
        List<StatesDTO> states= modelMap.map(statesFactory.listStates.values(),List.class);
        return states;
    }
    @Override
    public StatesDTO getStateById(StatesDTO statesDTO) throws UnsupportedStatesException {
        if(statesFactory.listStates.get(statesDTO.getId()) == null) throw new UnsupportedStatesException("Unsupported state");
        StatesDTO states=statesModelToDTO(statesFactory.listStates.get(statesDTO.getId()));
        return states;
    }
    @Override
    public ResultSalesDTO calcAmount(SalesDTO salesDTO) throws UnsupportedStatesException, EmptyDTOException, IllegalAccessException {
        validateForm(salesDTO);
        //Se valida que sea un estado que exista
        if(statesFactory.listStates.get(salesDTO.getId()) == null) throw new UnsupportedStatesException("Unsupported state");
        //Se obtiene el estado de acuerdo a su id
        StatesDTO states=statesModelToDTO(statesFactory.listStates.get(salesDTO.getId()));
        //Se realiza el calculo
        return calculationAmount.initCalculation(salesDTO,states);
    }
    //HAce conversion de objetos a DTO que es que debe viajar a la capa de controller
    private StatesDTO statesModelToDTO(StateModel stateModel){
        StatesDTO statesDTO= modelMap.map(stateModel,StatesDTO.class);
        return statesDTO;
    }
    //Se usa para validar los datos de entrada
    private void validateForm(SalesDTO salesDTO) throws EmptyDTOException {
        if(salesDTO == null) throw new EmptyDTOException("empty input data");
        if(salesDTO.getKm() == 0) throw new EmptyDTOException("the field KM cant be 0");
        if(salesDTO.getAmount() == 0) throw new EmptyDTOException("the field AMOUNT cant be 0");
        if(salesDTO.getEstimateType() == null || salesDTO.getEstimateType().trim().length() == 0) throw new EmptyDTOException("the field ESTIMATETYPE cant be empty");
    }
}
