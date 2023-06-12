package com.buttermove.factory;

import com.buttermove.model.StateModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class StatesFactory {
    public HashMap<String,StateModel> listStates = new HashMap<String,StateModel>();//Donde se guardan los registro en memoria de los estados
    //Metodos usadao para crar registro dentro del listado
    public HashMap<String,StateModel> createSate(String id,String name, double regularCommission,double premiumCommission){

        listStates.put(id,new StateModel(id,name,regularCommission,premiumCommission));
        return listStates;
    }


}
