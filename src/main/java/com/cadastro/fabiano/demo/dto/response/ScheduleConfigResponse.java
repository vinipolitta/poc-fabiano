package com.cadastro.fabiano.demo.dto.response;

import java.time.LocalTime;
import java.util.List;

public record ScheduleConfigResponse(
        LocalTime startTime,
        LocalTime endTime,
        int slotDurationMinutes,
        int maxDaysAhead,
        int slotCapacity,
        List<String> dedupFields
) {}
