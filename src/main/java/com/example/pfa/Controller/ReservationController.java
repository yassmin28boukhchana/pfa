package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestReservation;
import com.example.pfa.Dto.ResponseReservation;
import com.example.pfa.Repository.UserRepository;
import com.example.pfa.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/Reservations")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserRepository userRepository ;

    @PostMapping
    public ResponseEntity<Object> createReservation(@RequestBody RequestReservation requestReservation) {
        reservationService.createReservation(requestReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","save success !"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReservation> getReservationById(@PathVariable Long id) {
        ResponseReservation responseReservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(responseReservation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllReservations(){
        List <ResponseReservation> reservations = reservationService.getAllReservations() ;
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        Boolean result = reservationService.cancelReservation(id);

        if (result) {
            return ResponseEntity.ok("La réservation a été annulée avec succès.");
        } else {
            return ResponseEntity.badRequest().body("Échec de l'annulation de la réservation : la réservation n'a pas été trouvée.");
        }
    }


    @PutMapping("/{id}/confirm")
    public ResponseEntity<Object> confirmReservation(@PathVariable Long id) {
        Boolean result = reservationService.confirmReservation(id);

        if (result) {
            return ResponseEntity.ok("La réservation avec l'ID " + id + " a été confirmée avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReservationsByUserId(@PathVariable Long userId) {
        // Vérifiez d'abord si l'utilisateur existe
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            return ResponseEntity.badRequest().body("L'ID de l'utilisateur n'est pas valide.");
        }

        List<ResponseReservation> reservations = reservationService.getReservationByUserId(userId);
        if (reservations.isEmpty()) {
            return ResponseEntity.ok("Il n'existe pas de réservations pour cet utilisateur.");
        }

        return ResponseEntity.ok(reservations);
    }



}
