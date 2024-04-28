package org.gr40in.actuator.repository;

import org.gr40in.actuator.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
