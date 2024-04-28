package org.gr40in.actuator.service;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.gr40in.actuator.dao.Book;
import org.gr40in.actuator.dto.BookDto;
import org.gr40in.actuator.dto.BookMapper;
import org.gr40in.actuator.repository.BookRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDto)
                .toList();
    }

    public BookDto findBookById(Long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) return bookMapper.mapToDto(book.get());
        else throw new NoSuchElementException();
    }

    public BookDto createBook(BookDto book) {
        book.setId(null);
        Book saved = bookRepository.save(bookMapper.mapToBook(book));
        return bookMapper.mapToDto(saved);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public BookDto updateBook(BookDto bookDto) {
        var book = bookMapper.mapToBook(bookDto);
        var bookFromDBOptional = bookRepository.findById(book.getId());
        if (bookFromDBOptional.isPresent()) {
            var bookFromDB = bookFromDBOptional.get();
            bookFromDB.setName(book.getName());
        } else throw new NoSuchElementException();
        return bookMapper.mapToDto(book);
    }

    @EventListener(ContextRefreshedEvent.class)
    @Order(2)

    private void generateBooks() {
        if (bookRepository.findAll().isEmpty()) {
            Faker faker = new Faker();
            for (int i = 0; i < 30; i++) {
                bookRepository.save(Book.builder()
                        .name(faker.book().title())
                        .build());
            }
        }
    }
}
