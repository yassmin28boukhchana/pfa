package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestDepartement;
import com.example.pfa.Dto.ResponseDepartement;
import com.example.pfa.Entities.Departement;
import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Repository.DepartementRepository;
import com.example.pfa.Repository.EtablissementRepository;
import com.example.pfa.Service.DepartementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DepartementServiceImpl implements DepartementService {
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EtablissementRepository etablissementRepository ;


    @Override
    public void CreateDepartement(RequestDepartement requestDepartement) {
        Etablissement etablissement = etablissementRepository.findById(requestDepartement.getIdEtablissement()).orElseThrow();
        Departement departement = Departement.builder()        // najmou f 3oudh  nestaamlou l builder nestaaamlou l constructeur departement = new Departement w baad department.setName(Request.getname())
                .name(requestDepartement.getName())
                .status(true)
                .etablissement(etablissement)
                .build();
        departementRepository.save(departement);
    }

    @Override
    public List<ResponseDepartement> getAllDepartement() {
        List<Departement> departements = departementRepository.findAll() ;
        List<ResponseDepartement> departementFormated = new ArrayList<>() ;
        for(Departement departement:departements){
            ResponseDepartement departementF = ResponseDepartement.makeDepartement(departement);
            departementFormated.add(departementF);
        }
        return departementFormated ;

    }

    @Override
    public ResponseDepartement getDepartementById(Long id) {
        Departement departement = departementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée pour cet id :: " + id)) ;   // or ElseThrow kan fergha ken fergha trajaali erreur
        return ResponseDepartement.makeDepartement(departement);
    }

    @Override
    public Departement UpdateDepartement(Long id, RequestDepartement requestDepartement) {
        Departement departement = departementRepository.findById(id).orElseThrow() ;
        if(requestDepartement.getName()!= null){
            departement.setName(requestDepartement.getName());
        }
        else if (requestDepartement.getStatus() != null) {
            departement.setStatus(requestDepartement.getStatus());
        }

        return departementRepository.save(departement);
    }

    @Override
    public boolean DeleteDepartement(Long id) {
        if (! departementRepository.existsById(id)){
            return false ;
        }
        departementRepository.deleteById(id);
        return true;
    }

}
