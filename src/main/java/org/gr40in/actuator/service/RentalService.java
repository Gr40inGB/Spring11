package org.gr40in.actuator.service;

import lombok.RequiredArgsConstructor;
import org.gr40in.actuator.dao.Rental;
import org.gr40in.actuator.dao.Book;
import org.gr40in.actuator.dto.RentalDto;
import org.gr40in.actuator.dto.RentalMapper;
import org.gr40in.actuator.metric.CustomMetric;
import org.gr40in.actuator.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final CustomMetric metric;

    public List<RentalDto> findAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();
    }

    public RentalDto findRentalById(long id) {
        var rental = rentalRepository.findById(id);
        if (rental.isPresent()) return rentalMapper.toDto(rental.get());
        else throw new NoSuchElementException(" Error N" + metric.getErrorsCount().incrementAndGet()
                +". Cant find rental with id " + id);
    }

    public RentalDto createRental(RentalDto rentalDto) {
        rentalDto.setId(null);
        var rental = rentalRepository.save(rentalMapper.toEntity(rentalDto));
        metric.getBookRentCount().incrementAndGet();
        System.out.println(metric.getBookRentCount());;
        return rentalMapper.toDto(rental);
    }

    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        var rentalOptional = rentalRepository.findById(id);
        if (rentalOptional.isPresent()) {
            var newDate = rentalMapper.toEntity(rentalDto);
            Rental newData = rentalMapper.toEntity(rentalDto);
            var rental = rentalOptional.get();
            rental.setBook(newData.getBook());
            rental.setClient(newData.getClient());
            return rentalMapper.toDto(rental);
        } else
            throw new NoSuchElementException(" Error N" + metric.getErrorsCount().incrementAndGet()
                    + ". Cant find rental with id " + rentalDto.getId());
    }

    public boolean deleteRental(Long id) {
        try {
            rentalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throwAndRegisterInMetric(". Cant find rental with id " + id);
            return false;
        }
    }

    private void throwAndRegisterInMetric(String message) {
        int incremented = metric.getErrorsCount().incrementAndGet();
        throw new NoSuchElementException(" Error N " + incremented + " " + message);
    }

}
