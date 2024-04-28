package org.gr40in.actuator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalDto {
    private Long id;
    private Long book;
    private Long client;
}
