package com.exam.dto;

import com.exam.entity.StatutCredit;
import com.exam.entity.TypeRemboursement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcception;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;
    private String typeCredit;
    private String motif;
    private String typeBien;
    private String raisonSociale;
}
