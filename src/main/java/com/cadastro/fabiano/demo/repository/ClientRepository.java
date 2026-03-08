package com.cadastro.fabiano.demo.repository;


import com.cadastro.fabiano.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}