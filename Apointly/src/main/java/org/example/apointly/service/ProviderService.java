package org.example.apointly.service;
import org.example.apointly.dto.CreateProviderRequest;
import org.example.apointly.dto.ProviderDto;

import java.util.List;

public interface ProviderService {

    ProviderDto getProviderById(Long providerId);
    ProviderDto getProviderByEmail(String email);
    ProviderDto createProvider(CreateProviderRequest createProviderRequest);
    List<ProviderDto> getAllProviders();

}
