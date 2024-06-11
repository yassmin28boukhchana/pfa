package com.example.pfa.Service;

import com.example.pfa.Dto.RequestEtablissement;
import com.example.pfa.Dto.ResponseEtablissement;
import com.example.pfa.Entities.Departement;
import com.example.pfa.Entities.Etablissement;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EtablissementService {
    List<ResponseEtablissement> getAllEtablissement();

    ResponseEtablissement getEtablissementById(Long id);

    boolean DeleteEtablissement(Long id);

    void createEtablissement(RequestEtablissement requestEtablissement);

    public Etablissement UpdateEtablissement(Long id, RequestEtablissement requestEtablissement);
    List<Departement> getDepartementsByEtablissementId(Long etablissementId);

}