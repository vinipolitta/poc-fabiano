package com.cadastro.fabiano.demo.repository;

import com.cadastro.fabiano.demo.entity.Client;
import com.cadastro.fabiano.demo.entity.FormTemplate;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    Page<FormTemplate> findByClient(Client client, Pageable pageable);

    Optional<FormTemplate> findBySlug(String slug);

    /**
     * Busca o template com PESSIMISTIC_WRITE lock para serializar bookings concorrentes.
     * Garante que apenas um agendamento por slot seja processado por vez.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM FormTemplate t WHERE t.id = :id")
    Optional<FormTemplate> findByIdWithLock(@Param("id") Long id);

    boolean existsBySlug(String slug);

    long countByHasScheduleTrue();

    long countByHasScheduleFalseAndHasAttendanceTrue();

    long countByHasScheduleFalseAndHasAttendanceFalse();

    long countByClientAndHasScheduleTrue(Client client);

    long countByClientAndHasScheduleFalseAndHasAttendanceTrue(Client client);

    long countByClientAndHasScheduleFalseAndHasAttendanceFalse(Client client);
}
