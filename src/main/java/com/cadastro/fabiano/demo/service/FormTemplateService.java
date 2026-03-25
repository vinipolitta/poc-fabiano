package com.cadastro.fabiano.demo.service;

import com.cadastro.fabiano.demo.dto.request.CreateFormTemplateRequest;
import com.cadastro.fabiano.demo.dto.response.FormFieldResponse;
import com.cadastro.fabiano.demo.dto.response.FormTemplateResponse;
import com.cadastro.fabiano.demo.entity.FormField;
import com.cadastro.fabiano.demo.entity.FormTemplate;
import com.cadastro.fabiano.demo.entity.User;
import com.cadastro.fabiano.demo.repository.FormTemplateRepository;
import com.cadastro.fabiano.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormTemplateService {

    private final FormTemplateRepository templateRepository;
    private final UserRepository userRepository;

    public FormTemplateService(FormTemplateRepository templateRepository,
                               UserRepository userRepository) {
        this.templateRepository = templateRepository;
        this.userRepository = userRepository;
    }

    // ==========================
    // 🔹 LISTAR TODOS OS TEMPLATES (ADMIN)
    // ==========================
    public List<FormTemplateResponse> findAllTemplates() {
        return templateRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ==========================
    // 🔹 LISTAR TEMPLATES DO USUÁRIO LOGADO
    // ==========================
    public List<FormTemplateResponse> findTemplatesByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return templateRepository.findByClient(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ==========================
    // 🔹 CRIAR TEMPLATE
    // ==========================
    @Transactional
    public FormTemplateResponse createTemplate(CreateFormTemplateRequest request, Long clientId) {

        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        FormTemplate template = new FormTemplate();
        template.setName(request.name());
        template.setClient(client);

        // 🔥 SLUG
        template.setSlug(generateUniqueSlug(request.name()));

        // 🔹 Campos do formulário
        List<FormField> fields = request.fields().stream()
                .map(f -> {
                    FormField field = new FormField();
                    field.setLabel(f.label());
                    field.setType(f.type());
                    field.setFormTemplate(template);
                    return field;
                })
                .toList();

        template.setFields(fields);

        FormTemplate saved = templateRepository.save(template);
        return toResponse(saved);
    }

    // ==========================
    // 🔹 BUSCAR TEMPLATE POR SLUG
    // ==========================
    public FormTemplateResponse findBySlug(String slug) {
        FormTemplate template = templateRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Template não encontrado"));

        return toResponse(template);
    }

    // ==========================
    // 🔹 SLUG GENERATOR
    // ==========================
    private String generateSlug(String name) {
        return name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }

    private String generateUniqueSlug(String name) {
        String base = generateSlug(name);
        String slug = base;
        int count = 1;

        while (templateRepository.existsBySlug(slug)) {
            slug = base + "-" + count++;
        }

        return slug;
    }

    // ==========================
    // 🔹 MAPPER PARA DTO
    // ==========================
    private FormTemplateResponse toResponse(FormTemplate template) {

        List<FormFieldResponse> fields = template.getFields().stream()
                .map(f -> new FormFieldResponse(
                        f.getId(),
                        f.getLabel(),
                        f.getType()
                ))
                .toList();

        return new FormTemplateResponse(
                template.getId(),
                template.getName(),
                template.getSlug(),
                template.getClient().getName(), // pega o nome da empresa
                fields
        );
    }
}