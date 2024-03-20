package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestEtablissement;
import com.example.pfa.Dto.ResponseEtablissement;
import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Repository.EtablissementRepository;
import com.example.pfa.Service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EtablissementServiceImpl implements EtablissementService {
@Autowired
    private EtablissementRepository etablissementRepository ;
    @Override
    public List<ResponseEtablissement> getAllEtablissement() {
        List <Etablissement> etablissements = etablissementRepository.findAll();
        List <ResponseEtablissement> etablissementFormated = new ArrayList<>() ;
        for(Etablissement etablissement:etablissements){
            ResponseEtablissement etablissement1 = ResponseEtablissement.makeEtablissement(etablissement);
            etablissementFormated.add(etablissement1);
        }
        return etablissementFormated;
    }

    @Override
    public ResponseEtablissement getEtablissementById(Long id) {
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow();
        return ResponseEtablissement.makeEtablissement(etablissement);
    }

    @Override
    public boolean DeleteEtablissement(Long id) {
        if(! etablissementRepository.existsById(id)){
            return false ;
        }
        etablissementRepository.deleteById(id);
        return true;
    }

    @Override
    public void createEtablissement(RequestEtablissement requestEtablissement) {
        Etablissement etablissement = Etablissement.builder()
                .Nom(requestEtablissement.getNom())
                .Adresse(requestEtablissement.getAdresse())
                .status(requestEtablissement.getStatus())
                .Email(requestEtablissement.getEmail())
                .build();
        etablissementRepository.save(etablissement);
    }

    @Override
    public Etablissement UpdateEtablissement(Long id, RequestEtablissement requestEtablissement) {
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow();
        if(requestEtablissement.getNom() != null){
            etablissement.setNom(requestEtablissement.getNom());
        }
        else if(requestEtablissement.getEmail() != null ){
            etablissement.setEmail(requestEtablissement.getEmail());
        }
        else if (requestEtablissement.getAdresse() != null) {
            etablissement.setAdresse(requestEtablissement.getAdresse());
        }
        else if (requestEtablissement.getStatus() != null) {
            etablissement.setStatus(requestEtablissement.getStatus());
        }
        return etablissementRepository.save(etablissement);
    }


}

