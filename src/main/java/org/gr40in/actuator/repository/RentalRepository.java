package org.gr40in.actuator.repository;

import org.gr40in.actuator.dao.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
