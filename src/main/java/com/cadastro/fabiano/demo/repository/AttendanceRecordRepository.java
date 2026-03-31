package com.cadastro.fabiano.demo.repository;

import com.cadastro.fabiano.demo.entity.AttendanceRecord;
import com.cadastro.fabiano.demo.entity.FormTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findByFormTemplateOrderByRowOrderAscCreatedAtAsc(FormTemplate template);

    void deleteByFormTemplate(FormTemplate template);
}
