package com.exam.mapper;

import com.exam.dto.RemboursementDTO;
import com.exam.entity.Credit;
import com.exam.entity.Remboursement;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    public RemboursementDTO toDTO(Remboursement remboursement) {
        if (remboursement == null) {
            return null;
        }

        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit() != null ? remboursement.getCredit().getId() : null);

        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto, Credit credit) {
        if (dto == null) {
            return null;
        }

        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
        remboursement.setCredit(credit);

        return remboursement;
    }
}
