package com.exam.repository;

import com.exam.entity.CreditImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
}
