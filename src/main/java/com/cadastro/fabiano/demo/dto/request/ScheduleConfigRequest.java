package com.cadastro.fabiano.demo.dto.request;

import java.time.LocalTime;
import java.util.List;

public record ScheduleConfigRequest(
        LocalTime startTime,
        LocalTime endTime,
        int slotDurationMinutes,
        int maxDaysAhead,
        int slotCapacity,
        /**
         * Campos usados como chave de deduplicação.
         * Lista vazia = sem restrição (múltiplos agendamentos permitidos).
         */
        List<String> dedupFields
) {}
