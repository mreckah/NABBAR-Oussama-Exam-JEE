package com.exam.service;

import com.exam.dto.RemboursementDTO;
import java.util.List;

public interface RemboursementService {
    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);

    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
}
