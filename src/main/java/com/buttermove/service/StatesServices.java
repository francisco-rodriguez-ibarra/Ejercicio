package com.buttermove.service;

import com.buttermove.dto.ResultSalesDTO;
import com.buttermove.dto.SalesDTO;
import com.buttermove.dto.StatesDTO;
import com.buttermove.errors.EmptyDTOException;
import com.buttermove.errors.UnsupportedStatesException;
import com.buttermove.factory.StatesFactory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StatesServices {
    List<StatesDTO> getAllStates();
    StatesDTO getStateById(StatesDTO statesDTO) throws UnsupportedStatesException;
    ResultSalesDTO calcAmount(SalesDTO salesDTO) throws UnsupportedStatesException, EmptyDTOException, IllegalAccessException;
}
