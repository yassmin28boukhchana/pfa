package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestReservation;
import com.example.pfa.Dto.ResponseReservation;
import com.example.pfa.Dto.UpdateReservationRequest;
import com.example.pfa.Entities.*;
import com.example.pfa.Repository.BienRepository;
import com.example.pfa.Repository.ReservationRepository;
import com.example.pfa.Repository.UserRepository;
import com.example.pfa.Service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BienRepository bienRepository;

    @Override
    public void createReservation(RequestReservation requestReservation) {
        User user = userRepository.findById(requestReservation.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User non trouvé pour cet id :: " + requestReservation.getUserId()));

        List<Bien> biens = requestReservation.getBienIds().stream()
                .map(bienId -> bienRepository.findById(bienId)
                        .orElseThrow(() -> new EntityNotFoundException("Bien non trouvé pour cet id :: " + bienId)))
                .collect(Collectors.toList());

        Reservation reservation = Reservation.builder()
                .dateDebut(requestReservation.getDateDebut())
                .dateFin(requestReservation.getDateFin())
                .status(ReservationStatus.PENDING)
                .user(user)
                .build();

        reservationRepository.save(reservation);

        // Create LigneReservation entries for each Bien
        for (Bien bien : biens) {
            LigneReservation ligneReservation = new LigneReservation();
            ligneReservation.setBien(bien);
            ligneReservation.setReservation(reservation);
            reservation.getLigneReservations().add(ligneReservation);
        }

        reservationRepository.save(reservation);
    }

    @Override
    public List<ResponseReservation> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ResponseReservation::makeReservationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseReservation getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation non trouvé pour cet id :: " + id));
        return ResponseReservation.makeReservationResponse(reservation);
    }

    @Override
    public boolean deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            return false;
        }
        reservationRepository.deleteById(id);
        return true;
    }

    @Override
    public Reservation updateReservation(Long id, RequestReservation requestReservation) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation non trouvé pour cet id :: " + id));

        if (requestReservation.getDateDebut() != null) {
            reservation.setDateDebut(requestReservation.getDateDebut());
        }
        if (requestReservation.getDateFin() != null) {
            reservation.setDateFin(requestReservation.getDateFin());
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void approveReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation non trouvé pour cet id :: " + id));
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);
    }

    @Override
    public void rejectReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation non trouvé pour cet id :: " + id));
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }
}
