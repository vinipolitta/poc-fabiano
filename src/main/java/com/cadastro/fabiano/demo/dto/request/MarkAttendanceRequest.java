package com.cadastro.fabiano.demo.dto.request;

public record MarkAttendanceRequest(
        boolean attended,
        String notes
) {}
