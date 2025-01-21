package com.acme.springbootbootstrapproject.controller;

import com.acme.springbootbootstrapproject.entity.Flights;
import com.acme.springbootbootstrapproject.services.TafFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class TafFlightController {

    @Autowired
    private TafFlightService tafFlightService;

    // Get all flights
    @GetMapping
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(tafFlightService.getAllFlights());
    }

    // Get a specific flight
    @GetMapping("/{flightId}")
    public ResponseEntity<Flights> getFlightById(@PathVariable Long flightId) {
        Optional<Flights> flight = tafFlightService.getFlightById(flightId);
        return flight.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a new flight
    @PostMapping
    public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
        Flights createdFlight = tafFlightService.addFlight(flight);
        return ResponseEntity.ok(createdFlight);
    }

    // Update flight details
    @PutMapping("/{flightId}")
    public ResponseEntity<Flights> updateFlight(@PathVariable Long flightId, @RequestBody Flights updatedFlight) {
        try {
            Flights flight = tafFlightService.updateFlight(flightId, updatedFlight);
            return ResponseEntity.ok(flight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a flight
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        tafFlightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }
}