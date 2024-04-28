package org.gr40in.actuator.dto;

import lombok.RequiredArgsConstructor;
import org.gr40in.actuator.dao.Book;
import org.gr40in.actuator.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final String DELIMITER = ",";

    private final BookRepository bookRepository;

    public BookDto mapToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        return bookDto;
    }

    public Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        return book;
    }

    public List<Book> mapToListOfBook(List<BookDto> bookDtoList) {
        return bookDtoList.stream().map(this::mapToBook).toList();
    }

    public List<BookDto> mapToListOfBookDto(List<Book> bookList) {
        return bookList.stream().map(this::mapToDto).toList();
    }

}
