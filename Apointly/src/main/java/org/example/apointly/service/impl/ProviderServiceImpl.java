package org.example.apointly.service.impl;

import org.example.apointly.exception.EmailAlreadyExistsException;
import org.example.apointly.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.apointly.dto.CreateProviderRequest;
import org.example.apointly.dto.ProviderDto;
import org.example.apointly.entity.Provider;
import org.example.apointly.mapper.ProviderMapper;
import org.example.apointly.repository.ProviderRepository;
import org.example.apointly.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;


    @Override
    @Transactional
    public ProviderDto createProvider(CreateProviderRequest createProviderRequest) {
        // 1. CHEF'S RULE: Check if the email is already in use.
        providerRepository.findByEmail(createProviderRequest.getEmail())
                .ifPresent(existingProvider -> {
                    throw new EmailAlreadyExistsException("Email '" + createProviderRequest.getEmail() + "' is already in use.");
                });

        // 2. TRANSLATE: Use the mapper to turn the request DTO into a database Entity.
        Provider providerToSave = providerMapper.toProviderEntity(createProviderRequest);

        // 3. COOK & STORE: Tell the repository to save the new provider entity in the database.
        Provider savedProvider = providerRepository.save(providerToSave);

        // 4. TRANSLATE & SERVE: Translate the saved entity (which now has an ID!) back to a DTO to send back to the waiter (controller).
        return providerMapper.toProviderDto(savedProvider);
    };


    // Get one provider by their ID
    @Override
    @Transactional(readOnly = true) // Optimization: tell Spring this is a read-only operation
    public ProviderDto getProviderById(Long providerId) {
        // 1. GET FROM FRIDGE: Ask the repository to find a provider with this ID.
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with ID: " + providerId));

        // 2. TRANSLATE & SERVE: Convert the found entity to a DTO and return it.
        return providerMapper.toProviderDto(provider);
    }

    @Override
    public ProviderDto getProviderByEmail(String email) {
        return null;
        // To do later if needed
    }

    // Get all providers
    @Override
    @Transactional(readOnly = true)
    public List<ProviderDto> getAllProviders() {
        // 1. GET EVERYTHING: Ask the repository for all providers.
        List<Provider> providers = providerRepository.findAll();

        // 2. TRANSLATE & SERVE: Use our handy list mapper to convert the whole list of entities to a list of DTOs.
        return providerMapper.toProviderDtoList(providers);
    }

}


