package com.buttermove.Calculation;

import com.buttermove.dto.ResultSalesDTO;
import com.buttermove.dto.SalesDTO;
import com.buttermove.dto.StatesDTO;
import com.buttermove.errors.UnsupportedStatesException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculationAmount {
    //Inicia calculo
    public ResultSalesDTO initCalculation(SalesDTO salesDTO, StatesDTO statesDTO) throws UnsupportedStatesException {
        String date = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ssZ").format(new Date());//Se obtiene la fehca
        double result=calculationAmount(salesDTO,statesDTO);
        return new ResultSalesDTO(date,result);
    }
    private double calculationAmount(SalesDTO salesDTO, StatesDTO statesDTO) throws UnsupportedStatesException {
        double result=0;
        //Se inicia validado que opcion de estado es el que se tiene como dato de entrada
        switch (salesDTO.getId()){
           case "NY":
               result=discountNY(salesDTO,statesDTO);
               break;
            case "CA":
                //result=calcAmountAndCommission(salesDTO.getAmount(),statesDTO.getRegularCommission());
                result=discountAZAndCA(salesDTO,statesDTO);
                break;
            case "AZ":
               // result=calcAmountAndCommission(salesDTO.getAmount(),statesDTO.getRegularCommission());
                result=discountAZAndCA(salesDTO,statesDTO);
                break;
            case "TX":
                result=discountOHAndTX(salesDTO,statesDTO);
                break;
            case "OH":
                result=discountOHAndTX(salesDTO,statesDTO);
                break;
            default:
                result=0;
                throw new UnsupportedStatesException("Unsupported state");

        }
        return result;
    }
    //Realiza calculo para estado de NY
    private double discountNY(SalesDTO salesDTO, StatesDTO statesDTO){
        double discount= 0;//Variable usada para obtener % de descuento
        double amount=0;
        //Se optiene la comicion si es PREMIUM o es NORMAL
        double comission=salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") ? statesDTO.getPremiumCommission() : statesDTO.getRegularCommission();
        if(salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") && salesDTO.getKm() >25){//Se valida que sea premium y mas de 25KM
            amount=calcAmountAndCommission(salesDTO.getAmount(),comission);//Se realiza calculo de monto con comision
            discount=.05;//Se obtiene dato de descuento
        }else{
            amount=calcAmountAndCommission(salesDTO.getAmount(),comission);//Se realiza calculo de monto con comision
            amount-=(amount * .21);//Se le aplica el iva de 21%
            discount=0.0;//Se obtiene dato de descuento
        }
        //Realiza el calculo restandole el descuento correspondiente
        return  amount - discount(amount,discount);
    }
    private double discountOHAndTX(SalesDTO salesDTO, StatesDTO statesDTO){
        double discount= 0;
        double amount=0;
        double amountToDiscount=0;
        double comission=salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") ? statesDTO.getPremiumCommission() : statesDTO.getRegularCommission();

        if(salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") && salesDTO.getKm() >25){
            amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
            amountToDiscount=amount;
            discount=.05;
        }else{
            if(salesDTO.getKm() >=20 && salesDTO.getKm() <=30){
                amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
                amountToDiscount=salesDTO.getAmount();
                discount=.03;
            }else if (salesDTO.getKm() >30) {
                amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
                amountToDiscount=amount;
                discount=.05;
            }
        }
        //Realiza el calculo restandole el descuento correspondiente
        return  amount - discount(amountToDiscount,discount);
    }
    private double discountAZAndCA(SalesDTO salesDTO, StatesDTO statesDTO){
        double discount= 0;
        double amount=0;
        double comission=salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") ? statesDTO.getPremiumCommission() : statesDTO.getRegularCommission();
        if(salesDTO.getEstimateType().toUpperCase().equals("PREMIUM") && salesDTO.getKm() >25){
            amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
            discount=.05;
        }else{
            if(salesDTO.getKm() >26){
                amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
                discount=.05;
            }else{
                amount=calcAmountAndCommission(salesDTO.getAmount(),comission);
                discount=0.0;
            }
        }
        //Realiza el calculo restandole el descuento correspondiente
        return  amount - discount(amount,discount);
    }
    //Metodo que realiza el descuento
    private double discount(double amount,double discount){
        return amount * discount;
    }
    // Metodo que realiza la comision
    private double calcAmountAndCommission(double amount,double commission){
        return amount + (commission * amount);
    }
}
