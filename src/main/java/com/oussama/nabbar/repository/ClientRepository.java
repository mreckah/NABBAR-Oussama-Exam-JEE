package com.oussama.nabbar.repository;


import com.oussama.nabbar.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
