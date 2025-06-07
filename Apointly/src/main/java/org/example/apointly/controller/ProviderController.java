package org.example.apointly.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.apointly.dto.CreateProviderRequest;
import org.example.apointly.dto.ProviderDto;
import org.example.apointly.service.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this as a REST controller, where every method returns a domain object instead of a view
@RequestMapping("/api/providers") // Maps all requests starting with /api/providers to this controller
@RequiredArgsConstructor // Lombok: Creates the constructor for dependency injection
public class ProviderController {

    private final ProviderService providerService; // Inject our service

    /**
     * Endpoint to create a new provider.
     * HTTP Method: POST
     * URL: http://localhost:8080/api/providers
     */
    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody CreateProviderRequest createProviderRequest) {
        ProviderDto createdProvider = providerService.createProvider(createProviderRequest);
        // Return HTTP status 201 Created along with the created provider in the response body
        return new ResponseEntity<>(createdProvider, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get a provider by their ID.
     * HTTP Method: GET
     * URL: http://localhost:8080/api/providers/{id} (e.g., /api/providers/1)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getProviderById(@PathVariable Long id) {
        ProviderDto providerDto = providerService.getProviderById(id);
        // Return HTTP status 200 OK with the found provider
        return ResponseEntity.ok(providerDto);
    }

    /**
     * Endpoint to get all providers.
     * HTTP Method: GET
     * URL: http://localhost:8080/api/providers
     */
    @GetMapping
    public ResponseEntity<List<ProviderDto>> getAllProviders() {
        List<ProviderDto> providers = providerService.getAllProviders();
        // Return HTTP status 200 OK with the list of providers
        return ResponseEntity.ok(providers);
    }
}