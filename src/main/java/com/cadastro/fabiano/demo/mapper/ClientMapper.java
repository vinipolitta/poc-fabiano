package com.cadastro.fabiano.demo.mapper;


import com.cadastro.fabiano.demo.dto.request.ClientRequest;
import com.cadastro.fabiano.demo.dto.response.ClientResponse;
import com.cadastro.fabiano.demo.entity.Client;

public class ClientMapper {

    public static Client toEntity(ClientRequest dto){

        return Client.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .company(dto.company())
                .notes(dto.notes())
                .build();

    }

    public static ClientResponse toDTO(Client client){

        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getCompany(),
                client.getNotes()
        );

    }

}