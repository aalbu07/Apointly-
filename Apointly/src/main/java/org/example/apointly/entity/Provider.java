package org.example.apointly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Generates a no-argument constructor
@AllArgsConstructor // Lombok: Generates a constructor with all arguments
@Entity // JPA: Marks this class as a JPA entity (a table in the database)
@Table(name = "providers") // JPA: Specifies the table name in the database
public class Provider {

    @Id // JPA: Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA: Configures auto-generation of the ID (database handles it)
    private Long id;

    @NotBlank(message = "Provider name cannot be blank") // Validation: Ensures the name is not null and not just whitespace
    @Size(min = 2, max = 100, message = "Provider name must be between 2 and 100 characters") // Validation: Name length
    @Column(nullable = false, length = 100) // JPA: Maps to a column, cannot be null, max length 100
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address") // Validation: Checks for valid email format
    @Column(nullable = false, unique = true, length = 100) // JPA: Cannot be null, must be unique, max length 100
    private String email;

    @Size(max = 20, message = "Phone number must be less than 20 characters")
    @Column(length = 20) // JPA: Max length 20
    private String phoneNumber; // Optional

    @Column(columnDefinition = "TEXT") // JPA: For potentially longer text, maps to TEXT type in many DBs
    private String otherContactDetails; // Optional

    @CreationTimestamp // Hibernate: Automatically sets this field to the current timestamp when a provider is created
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Hibernate: Automatically updates this field to the current timestamp when a provider is updated
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
