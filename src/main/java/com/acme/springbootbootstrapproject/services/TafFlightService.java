package com.acme.springbootbootstrapproject.services;

import com.acme.springbootbootstrapproject.entity.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TafFlightService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9200/datastore";

    // Retrieve all flights
    public List<Flights> getAllFlights() {
        String url = BASE_URL + "/flights";

        ResponseEntity<List<Flights>> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Flights>>() {});

        return response.getBody();
    }

    // Retrieve a specific flight
    public Optional<Flights> getFlightById(Long flightId) {
        String url = BASE_URL + "/flights/" + flightId;

        ResponseEntity<Flights> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Flights>() {});

        return Optional.ofNullable(response.getBody());
    }

    // Add a new flight
    public Flights addFlight(Flights flight) {
        String url = BASE_URL + "/flights";

        // Create the HTTP headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // Set Content-Type header

        // Create the HTTP entity with user object and headers
        HttpEntity<Flights> entity = new HttpEntity<>(flight, headers);

        ResponseEntity<Flights> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.POST,
                entity,
                Flights.class);

        return response.getBody();
    }

    // Update flight details
    public Flights updateFlight(Long flightId, Flights updatedFlight) {
        String url = BASE_URL + "/flights/" + flightId;

        // Create the HTTP headers (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  // Set Content-Type header

        // Create the HTTP entity with user object and headers
        HttpEntity<Flights> entity = new HttpEntity<>(updatedFlight, headers);

        ResponseEntity<Flights> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.PUT,
                entity,
                Flights.class);

        return response.getBody();
    }

    // Delete a flight
    public void deleteFlight(Long flightId) {
        String url = BASE_URL + "/flights/" + flightId;

        ResponseEntity<Flights> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Flights.class);
    }
}
