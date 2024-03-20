package com.example.pfa.Repository;

import com.example.pfa.Entities.LigneReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneReservationRepository extends JpaRepository<LigneReservation,Long> {

}
