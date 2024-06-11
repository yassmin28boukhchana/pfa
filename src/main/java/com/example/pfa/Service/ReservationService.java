package com.example.pfa.Service;

import com.example.pfa.Dto.RequestReservation;
import com.example.pfa.Dto.ResponseReservation;
import com.example.pfa.Dto.UpdateReservationRequest;
import com.example.pfa.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    void createReservation(RequestReservation requestReservation);
    List<ResponseReservation> getAllReservations();
    ResponseReservation getReservationById(Long id);
    boolean deleteReservation(Long id);
    Reservation updateReservation(Long id, RequestReservation requestReservation);
    void approveReservation(Long id);
    void rejectReservation(Long id);
}
