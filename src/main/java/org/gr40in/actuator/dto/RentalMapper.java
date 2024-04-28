package org.gr40in.actuator.dto;

import lombok.RequiredArgsConstructor;

import org.gr40in.actuator.dao.Book;
import org.gr40in.actuator.dao.Rental;
import org.gr40in.actuator.repository.BookRepository;
import org.gr40in.actuator.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    public Rental toEntity(RentalDto rentalDto) {

        var book = bookRepository.findById(rentalDto.getBook());
        var client = clientRepository.findById(rentalDto.getClient());
//        var clientEntity = client.get();
        if (book.isPresent() && client.isPresent())
            return Rental.builder()
                    .book(book.get())
                    .client(client.get())
                    .build();
        else throw new RuntimeException("Mapper brr");
    }

    public RentalDto toDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .client(rental.getClient().getId())
                .book(rental.getBook().getId())
                .build();
    }

}
