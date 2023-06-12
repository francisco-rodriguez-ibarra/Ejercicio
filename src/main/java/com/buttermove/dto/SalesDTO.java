package com.buttermove.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesDTO {
    private String id;
    private int km;
    private String estimateType;
    private double amount;
}
