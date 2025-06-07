package org.example.apointly.repository;
import org.example.apointly.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Spring annotation: Marks this interface as a Spring-managed repository component
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    // Spring Data JPA will automatically generate a query for this method
    // based on its name: "find by email"
    // It will return an Optional because a provider with a given email might not exist.
     Optional<Provider> findByEmail(String email);

    // You can add other custom query methods here if needed. For example:
    // List<Provider> findByNameContainingIgnoreCase(String nameSubstring);
    // boolean existsByEmail(String email);
}