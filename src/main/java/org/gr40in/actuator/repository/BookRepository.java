package org.gr40in.actuator.repository;

import org.gr40in.actuator.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
