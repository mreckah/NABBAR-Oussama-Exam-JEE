package com.exam.service;

import com.exam.dto.CreditDTO;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();

    CreditDTO getCreditById(Long id);

    CreditDTO createCredit(CreditDTO creditDTO);

    CreditDTO updateCredit(Long id, CreditDTO creditDTO);

    void deleteCredit(Long id);

    List<CreditDTO> getCreditsByClientId(Long clientId);
}
