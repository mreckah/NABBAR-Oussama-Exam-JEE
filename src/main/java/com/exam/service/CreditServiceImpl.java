package com.exam.service;

import com.exam.dto.CreditDTO;
import com.exam.entity.Client;
import com.exam.entity.Credit;
import com.exam.mapper.CreditMapper;
import com.exam.repository.ClientRepository;
import com.exam.repository.CreditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
            ClientRepository clientRepository,
            CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        return creditRepository.findById(id)
                .map(creditMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));
    }

    @Override
    public CreditDTO createCredit(CreditDTO creditDTO) {
        Credit credit = creditMapper.toEntity(creditDTO);
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé"));
            credit.setClient(client);
        }
        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.toDTO(savedCredit);
    }

    @Override
    public CreditDTO updateCredit(Long id, CreditDTO creditDTO) {
        Credit existingCredit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédit non trouvé"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setId(id);
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé"));
            credit.setClient(client);
        }

        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.toDTO(updatedCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toDTO)
                .collect(Collectors.toList());
    }
}
