package com.cadastro.fabiano.demo.repository;

import com.cadastro.fabiano.demo.entity.FormTemplate;
import com.cadastro.fabiano.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    List<FormTemplate> findByClient(User client);

    Optional<FormTemplate> findBySlug(String slug); // 🔥 NOVO

    boolean existsBySlug(String slug); // 🔥 NOVO
}