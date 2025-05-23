package org.example.apointly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // Marks this class as a JPA entity (maps to a database table).
@Data // Lombok annotation that auto-generates getters, setters, and toString.
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId; // Staff ID
    private Long customerId;
    private Long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // e.g., CONFIRMED, CANCELED
}