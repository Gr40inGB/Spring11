package org.gr40in.actuator.controller;

import lombok.RequiredArgsConstructor;

import org.gr40in.actuator.dao.Book;
import org.gr40in.actuator.dao.Client;
import org.gr40in.actuator.dto.RentalDto;
import org.gr40in.actuator.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @PostMapping("create")
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity.ok().body(rentalService.createRental(rentalDto));
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> findAllRental() {
        return ResponseEntity.ok().body(rentalService.findAllRentals());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRentalById(@PathVariable Long id) {
        return rentalService.deleteRental(id)
                ? ResponseEntity.accepted().build()
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok().body(rentalService.findRentalById(id));
    }

}
