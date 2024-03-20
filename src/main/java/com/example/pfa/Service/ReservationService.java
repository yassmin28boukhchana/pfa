package com.example.pfa.Service;

import com.example.pfa.Dto.RequestReservation;
import com.example.pfa.Dto.ResponseReservation;
import com.example.pfa.Dto.UpdateReservationRequest;
import com.example.pfa.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    List<ResponseReservation> getAllReservations();
    ResponseReservation getReservationById(Long id);
    void createReservation(RequestReservation requestReservation);
    Reservation updateReservation(Long id, UpdateReservationRequest requestReservation);
    Boolean cancelReservation(Long id);
    public Boolean confirmReservation(Long id) ;
    List<ResponseReservation> getReservationByUserId(Long userId);
}
