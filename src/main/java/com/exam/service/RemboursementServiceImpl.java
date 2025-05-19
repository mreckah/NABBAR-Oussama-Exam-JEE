package com.exam.service;

import com.exam.dto.RemboursementDTO;
import com.exam.entity.Remboursement;
import com.exam.mapper.RemboursementMapper;
import com.exam.repository.RemboursementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final RemboursementMapper remboursementMapper;

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Remboursement not found with id: " + id));
        return remboursementMapper.toDTO(remboursement);
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(savedRemboursement);
    }

    @Override
    public RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO) {
        if (!remboursementRepository.existsById(remboursementDTO.getId())) {
            throw new EntityNotFoundException("Remboursement not found with id: " + remboursementDTO.getId());
        }
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        Remboursement updatedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(updatedRemboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        if (!remboursementRepository.existsById(id)) {
            throw new EntityNotFoundException("Remboursement not found with id: " + id);
        }
        remboursementRepository.deleteById(id);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }
}
