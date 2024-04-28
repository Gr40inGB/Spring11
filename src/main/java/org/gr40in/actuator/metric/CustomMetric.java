package org.gr40in.actuator.metric;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Data
public class CustomMetric {
    private final AtomicInteger errorsCount;
    private final AtomicInteger bookRentCount;

    public CustomMetric(MeterRegistry meterRegistry) {
        errorsCount = meterRegistry.gauge("errors_count", new AtomicInteger());
        bookRentCount = meterRegistry.gauge("books_rent_count", new AtomicInteger());
    }

}
