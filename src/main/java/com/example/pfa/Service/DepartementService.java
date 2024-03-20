package com.example.pfa.Service;

import com.example.pfa.Dto.RequestDepartement;
import com.example.pfa.Dto.ResponseDepartement;
import com.example.pfa.Entities.Departement;

import java.util.List;

public interface DepartementService {
    void CreateDepartement(RequestDepartement requestDepartement );
    List<ResponseDepartement> getAllDepartement() ;
    ResponseDepartement getDepartementById(Long id);
    public Departement UpdateDepartement (Long id, RequestDepartement requestDepartement) ;
    boolean DeleteDepartement(Long id);

}
