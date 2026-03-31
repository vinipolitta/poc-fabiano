package com.cadastro.fabiano.demo.dto.request;

import java.util.List;
import java.util.Map;

public record ImportAttendanceRequest(
        List<Map<String, String>> rows
) {}
