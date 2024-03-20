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
    ReservationRepository reservationRepository ;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    BienRepository bienRepository ;
    public List<ResponseReservation> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll() ;
        List<ResponseReservation> responseReservations = new ArrayList<>();
        for(Reservation reservation : reservations){
            ResponseReservation responseReservation = ResponseReservation.makeReservationResponse(reservation);
            responseReservations.add(responseReservation);
        }
        return responseReservations;
    }

    @Override
    public ResponseReservation getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + id + " not found."));
        return ResponseReservation.makeReservationResponse(reservation);
    }

    @Override
    public void createReservation(RequestReservation requestReservation) {
        User user = userRepository.findById(requestReservation.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Récupère les biens par leurs IDs
        List<Bien> biens = (List<Bien>) bienRepository.findAllById(requestReservation.getBienIds());
        // Crée la réservation en utilisant le builder
        Reservation reservation = Reservation.builder()
                .date_Debut(requestReservation.getDateDebut())
                .date_Fin(requestReservation.getDateFin())
                .status(ReservationStatus.PENDING) // Supposons PENDING comme statut initial
                .user(user)
                .build();
        // Crée les lignes de réservation pour chaque bien et les ajoute à la réservation
        List<LigneReservation> ligneReservations = biens.stream().map(bien ->
                LigneReservation.builder()
                        .bien(bien)
                        .reservation(reservation)
                        .build()
        ).collect(Collectors.toList());

        // Si votre logique métier le nécessite, liez les lignes de réservation avec la réservation ici
        reservation.setLigneReservations(ligneReservations);

        // Sauvegarde la réservation avec les lignes de réservation associées
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        // Mise à jour des dates
        reservation.setDate_Debut(request.getDateDebut());
        reservation.setDate_Fin(request.getDateFin());
        // Gérer l'ajout de nouveaux biens
        if (request.getBiensToAdd() != null && !request.getBiensToAdd().isEmpty()) {
            List<Bien> biensToAdd = bienRepository.findAllById(request.getBiensToAdd());
            for (Bien bien : biensToAdd) {
                LigneReservation ligne = new LigneReservation();
                ligne.setBien(bien);
                ligne.setReservation(reservation);
                reservation.getLigneReservations().add(ligne);
            }
        }
        // Gérer la suppression de biens
        if (request.getBiensToRemove() != null && !request.getBiensToRemove().isEmpty()) {
            List<LigneReservation> lignesToRemove = reservation.getLigneReservations().stream()
                    .filter(ligne -> request.getBiensToRemove().contains(ligne.getBien().getId_bien()))
                    .collect(Collectors.toList());

            reservation.getLigneReservations().removeAll(lignesToRemove);
            // Assurez-vous de gérer correctement la suppression des entités LigneReservation de la base de données si nécessaire
        }

       return reservationRepository.save(reservation);

    }

    @Override
    public Boolean cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if(reservation == null){
            return false;
        }
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    public Boolean confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if(reservation == null){
            return false;
        }
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);
        return true;
    }

    @Override
    public List<ResponseReservation> getReservationByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream()
                .map(ResponseReservation::makeReservationResponse)
                .collect(Collectors.toList());
    }




}
