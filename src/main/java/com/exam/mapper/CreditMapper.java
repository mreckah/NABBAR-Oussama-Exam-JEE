package com.exam.mapper;

import com.exam.dto.CreditDTO;
import com.exam.entity.Credit;
import com.exam.entity.CreditImmobilier;
import com.exam.entity.CreditPersonnel;
import com.exam.entity.CreditProfessionnel;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {

    public CreditDTO toDTO(Credit credit) {
        if (credit == null) {
            return null;
        }

        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcception(credit.getDateAcception());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient() != null ? credit.getClient().getId() : null);

        if (credit instanceof CreditPersonnel) {
            dto.setTypeCredit("PERSONNEL");
            dto.setMotif(((CreditPersonnel) credit).getMotif());
        } else if (credit instanceof CreditImmobilier) {
            dto.setTypeCredit("IMMOBILIER");
            dto.setTypeBien(((CreditImmobilier) credit).getTypeBien());
        } else if (credit instanceof CreditProfessionnel) {
            dto.setTypeCredit("PROFESSIONNEL");
            dto.setMotif(((CreditProfessionnel) credit).getMotif());
            dto.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
        }

        return dto;
    }

    public Credit toEntity(CreditDTO dto) {
        if (dto == null) {
            return null;
        }

        Credit credit;
        String typeCredit = dto.getTypeCredit();

        if ("PERSONNEL".equals(typeCredit)) {
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setMotif(dto.getMotif());
            credit = creditPersonnel;
        } else if ("IMMOBILIER".equals(typeCredit)) {
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setTypeBien(dto.getTypeBien());
            credit = creditImmobilier;
        } else if ("PROFESSIONNEL".equals(typeCredit)) {
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setMotif(dto.getMotif());
            creditProfessionnel.setRaisonSociale(dto.getRaisonSociale());
            credit = creditProfessionnel;
        } else {
            throw new IllegalArgumentException("Type de crédit non valide: " + typeCredit);
        }

        credit.setId(dto.getId());
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcception(dto.getDateAcception());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());

        return credit;
    }
}