package com.oussama.nabbar.service;

import com.oussama.nabbar.dto.RemboursementDTO;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();

    RemboursementDTO getRemboursementById(Long id);

    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);

    RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO);

    void deleteRemboursement(Long id);

    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
}
