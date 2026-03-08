package com.cadastro.fabiano.demo.dto.response;


public record ClientResponse(

        Long id,
        String name,
        String email,
        String phone,
        String company,
        String notes

) {}