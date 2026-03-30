package com.cadastro.fabiano.demo.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record FormSubmissionResponse(
        Long id,
        Long templateId,
        Map<String, String> values,
        LocalDateTime createdAt
) {}