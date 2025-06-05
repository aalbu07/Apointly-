package org.example.apointly.repository;

import org.example.apointly.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
// at this point I'm telling Spring that JpaRepository will use my Appoiment entity to perform
// CRUD DB operations, and will identify them in DB using valueType Long
//Map<KeyType, ValueType>
