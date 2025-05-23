package org.example.apointly.controller;


import jakarta.validation.Valid;
import org.example.apointly.ApointlyApplication;
import org.example.apointly.model.Appointment;
import org.example.apointly.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid Appointment appointment) {

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.status(201).body(savedAppointment);
    }

}
