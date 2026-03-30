package com.cadastro.fabiano.demo.controller;

import com.cadastro.fabiano.demo.dto.request.CreateFormTemplateRequest;
import com.cadastro.fabiano.demo.dto.response.FormTemplateResponse;
import com.cadastro.fabiano.demo.service.FormTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form-templates")
public class FormTemplateController {

    private final FormTemplateService templateService;

    public FormTemplateController(FormTemplateService templateService) {
        this.templateService = templateService;
    }

    // 🔥 ADMIN cria form para cliente
    @PostMapping("/create/{clientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FormTemplateResponse> createTemplate(
            @PathVariable Long clientId,
            @RequestBody CreateFormTemplateRequest request) {

        return ResponseEntity.ok(
                templateService.createTemplate(request, clientId)
        );
    }

    // 🔥 CLIENTE vê seus forms
    @GetMapping("/my-templates")
    public ResponseEntity<List<FormTemplateResponse>> getMyTemplates(Authentication authentication) {

        String username = authentication.getName();

        return ResponseEntity.ok(
                templateService.findTemplatesByUsername(username)
        );
    }

    // 🔥 ADMIN vê todos
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FormTemplateResponse>> getAllTemplates() {

        return ResponseEntity.ok(
                templateService.findAllTemplates()
        );
    }

    // 🔥 acessar por slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity<FormTemplateResponse> getBySlug(@PathVariable String slug) {

        return ResponseEntity.ok(
                templateService.findBySlug(slug)
        );
    }
}