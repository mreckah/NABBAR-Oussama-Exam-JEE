package com.oussama.nabbar.dto;

import com.oussama.nabbar.entity.TypeRemboursement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private double montant;
    private TypeRemboursement type;
    private Long creditId;
}
