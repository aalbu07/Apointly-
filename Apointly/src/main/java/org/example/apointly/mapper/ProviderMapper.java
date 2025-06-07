package org.example.apointly.mapper;

import org.example.apointly.dto.CreateProviderRequest;
import org.example.apointly.dto.ProviderDto;
import org.example.apointly.entity.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Tells MapStruct to generate a Spring Bean

public interface ProviderMapper {

    // Optional: if you want to get an instance via Mappers.getMapper, not needed if using componentModel="spring"
    // ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    /**
     * Maps a Provider entity to a ProviderDto.
     * @param provider The Provider entity.
     * @return The corresponding ProviderDto.
     */

    ProviderDto toProviderDto(Provider provider);

    /**
     * Maps a CreateProviderRequest DTO to a Provider entity.
     * Note: id, createdAt, updatedAt are not mapped from the request
     * as they are handled by the database or system.
     * @param createProviderRequest The DTO for creating a provider.
     * @return The corresponding Provider entity.
     */
    @Mapping(target = "id", ignore = true) // Ensure ID is not mapped from request
    @Mapping(target = "createdAt", ignore = true) // createdAt is auto-generated
    @Mapping(target = "updatedAt", ignore = true) // updatedAt is auto-generated
    Provider toProviderEntity(CreateProviderRequest createProviderRequest);

    /**
     * Maps a list of Provider entities to a list of ProviderDto objects.
     * @param providers List of Provider entities.
     * @return List of corresponding ProviderDto objects.
     */
    List<ProviderDto> toProviderDtoList(List<Provider> providers);
}