package com.cadastro.fabiano.demo.service;


import com.cadastro.fabiano.demo.dto.request.ClientRequest;
import com.cadastro.fabiano.demo.dto.response.ClientResponse;
import com.cadastro.fabiano.demo.entity.Client;
import com.cadastro.fabiano.demo.mapper.ClientMapper;
import com.cadastro.fabiano.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public ClientResponse create(ClientRequest dto){

        Client client = ClientMapper.toEntity(dto);

        Client saved = repository.save(client);

        return ClientMapper.toDTO(saved);
    }

    public Page<ClientResponse> findAll(Pageable pageable){

        return repository
                .findAll(pageable)
                .map(ClientMapper::toDTO);

    }

    public ClientResponse findById(Long id){

        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return ClientMapper.toDTO(client);
    }

    public void delete(Long id){

        repository.deleteById(id);

    }

}
