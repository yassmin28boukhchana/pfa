package com.example.pfa.Service;

import com.example.pfa.Dto.RequestBien;
import com.example.pfa.Dto.ResponseBien;
import com.example.pfa.Dto.ResponseCategorie;
import com.example.pfa.Entities.Bien;

import java.util.List;

public interface BienService {
    void createBien (RequestBien requestBien) ;
    List<ResponseBien> getAllBiens();
    ResponseBien getBienById(Long id) ;
    Boolean deleteBien(Long id );
     Bien UpdateBien(Long id , RequestBien requestBien);
}
