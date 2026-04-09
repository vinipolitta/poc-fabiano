package com.cadastro.fabiano.demo.service;


import com.cadastro.fabiano.demo.dto.request.ClientRequest;
import com.cadastro.fabiano.demo.dto.response.ClientResponse;
import com.cadastro.fabiano.demo.entity.Client;
import com.cadastro.fabiano.demo.entity.FormTemplate;
import com.cadastro.fabiano.demo.mapper.ClientMapper;
import com.cadastro.fabiano.demo.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final FormTemplateService formTemplateService;


    @Transactional
    public ClientResponse createClient(ClientRequest dto) {

        // converte DTO em entidade com User
        Client client = ClientMapper.toEntityWithUser(dto, passwordEncoder);

        Client saved = repository.save(client);

        // converte para DTO de resposta
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

    @Transactional
    public void delete(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<FormTemplate> templates = client.getTemplates();

        // Remove imagens de cada template ANTES do soft-delete
        // (countUsingImageUrlExcluding usa o ID, então não precisa que o registro esteja deletado)
        if (templates != null) {
            templates.forEach(formTemplateService::deleteTemplateImages);
            templates.forEach(t -> t.setDeleted(true));
        }

        client.setDeleted(true);
        repository.save(client);
    }

}
