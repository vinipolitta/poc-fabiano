package com.cadastro.fabiano.demo.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record AttendanceRecordResponse(
        Long id,
        Long templateId,
        Map<String, String> rowData,
        boolean attended,
        LocalDateTime attendedAt,
        String notes,
        Integer rowOrder,
        LocalDateTime createdAt
) {}
