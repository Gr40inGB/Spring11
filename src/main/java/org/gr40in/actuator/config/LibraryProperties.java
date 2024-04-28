package org.gr40in.actuator.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "library")
public class LibraryProperties {
    private Integer allowed = 2;
}
