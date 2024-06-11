package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestReservation;
import com.example.pfa.Dto.ResponseReservation;
import com.example.pfa.Repository.UserRepository;
import com.example.pfa.Service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin', 'USER')")
    public ResponseEntity<List<ResponseReservation>> getAllReservations() {
        List<ResponseReservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('Admin', 'USER')")
    public ResponseEntity<ResponseReservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createReservation(@RequestBody @Valid RequestReservation requestReservation) {
        reservationService.createReservation(requestReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message", "Reservation created successfully!"));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteReservation(@PathVariable Long id) {
        boolean deleted = reservationService.deleteReservation(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Reservation deleted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Reservation does not exist!"));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateReservation(@PathVariable Long id, @RequestBody @Valid RequestReservation requestReservation) {
        reservationService.updateReservation(id, requestReservation);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Reservation updated successfully!"));
    }

    @PostMapping("{id}/approve")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> approveReservation(@PathVariable Long id) {
        reservationService.approveReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Reservation approved successfully!"));
    }

    @PostMapping("{id}/reject")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> rejectReservation(@PathVariable Long id) {
        reservationService.rejectReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Reservation rejected successfully!"));
    }

    @PostMapping("{id}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> cancelReservation(@PathVariable Long id) {
        reservationService.rejectReservation(id); // Treat cancel as reject for user
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Reservation cancelled successfully!"));
    }
}
