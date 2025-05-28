package com.example.trackingnumber.controller;

import com.example.trackingnumber.model.TrackingNumberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingNumberController {

    private final ConcurrentHashMap<String, Boolean> generatedNumbers = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1000000);

    @GetMapping
    public TrackingNumberResponse getNextTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam String weight,
            @RequestParam String created_at,
            @RequestParam String customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ) {
        String base = origin_country_id.toUpperCase()
                + destination_country_id.toUpperCase()
                + customer_slug.toUpperCase().replace("-", "").substring(0, Math.min(4, customer_slug.length()));

        String uniqueSuffix;
        String trackingNumber;

        do {
            uniqueSuffix = Long.toString(counter.getAndIncrement(), 36).toUpperCase();
            trackingNumber = (base + uniqueSuffix).substring(0, Math.min(16, base.length() + uniqueSuffix.length()));
        } while (generatedNumbers.putIfAbsent(trackingNumber, true) != null);

        return new TrackingNumberResponse(trackingNumber, OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
