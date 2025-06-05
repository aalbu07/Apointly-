package org.example.apointly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity (maps to a database table).
@Data // Lombok annotation that auto-generates getters, setters, and toString.
public class Appointment {
    @Id// Here we specify this field as a PK in DB
    @GeneratedValue(strategy = GenerationType.IDENTITY) // here we are saying that the DB will autoincrement ID
    private Long id;
    private Long userId; // Staff ID
    private Long customerId;
    private Long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
   // @NotBlank(message = "Name is required")
    private String status; // e.g., CONFIRMED, CANCELED
}