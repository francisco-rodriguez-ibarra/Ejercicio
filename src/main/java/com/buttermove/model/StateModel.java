package com.buttermove.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateModel {//Es el modelo de datos para los estados
    private String id;
    private String name;
    private double regularCommission;
    private double premiumCommission;
}
