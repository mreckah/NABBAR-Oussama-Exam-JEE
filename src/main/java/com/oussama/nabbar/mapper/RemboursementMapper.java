package com.oussama.nabbar.mapper;

import com.oussama.nabbar.dto.RemboursementDTO;
import com.oussama.nabbar.entity.Credit;
import com.oussama.nabbar.entity.Remboursement;
import com.oussama.nabbar.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemboursementMapper {

    private final CreditRepository creditRepository;

    public RemboursementDTO toDTO(Remboursement remboursement) {
        if (remboursement == null) {
            return null;
        }

        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit().getId());
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        if (dto == null) {
            return null;
        }

        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());

        if (dto.getCreditId() != null) {
            Credit credit = creditRepository.findById(dto.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found with id: " + dto.getCreditId()));
            remboursement.setCredit(credit);
        }

        return remboursement;
    }
}
