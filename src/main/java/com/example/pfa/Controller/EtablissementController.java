package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestEtablissement;
import com.example.pfa.Dto.ResponseEtablissement;
import com.example.pfa.Entities.Departement;
import com.example.pfa.Service.EtablissementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/Etablissements")
@RequiredArgsConstructor
public class EtablissementController {
    @Autowired
    public EtablissementService etablissementService ;
    @GetMapping
    @PreAuthorize("hasAnyRole('Admin', 'USER')")
    public ResponseEntity<List<ResponseEtablissement>> getAllEtablissement(){
        List<ResponseEtablissement> etablissements = etablissementService.getAllEtablissement();
        return ResponseEntity.ok(etablissements);
    }

  @GetMapping("{id}")
  @PreAuthorize("hasAnyRole('Admin', 'USER')")
    public ResponseEntity<ResponseEtablissement> getEtablissementById(@PathVariable Long id){
        return ResponseEntity.ok(etablissementService.getEtablissementById(id));
  }


  @DeleteMapping("{id}")
  @PreAuthorize("hasRole('Admin')")
   public ResponseEntity<Object>DeleteEtablissement(@PathVariable Long id){
        boolean deletedEtablissement = etablissementService.DeleteEtablissement(id);
        if(deletedEtablissement){
            return ResponseEntity.status(HttpStatus.OK).body( Collections.singletonMap("message"," delete success !!!"));
        }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message","Etablissement does not exist"));
  }


  @PostMapping
  @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> addEtablissement(@RequestBody  RequestEtablissement requestEtablissement){
        etablissementService.createEtablissement(requestEtablissement);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","save success !"));
  }

  @PutMapping("{id}")
  @PreAuthorize("hasRole('Admin')")
  public ResponseEntity<Object> UpdateEtablissement(@PathVariable(name="id") Long id, @RequestBody @Valid RequestEtablissement requestEtablissement){
        etablissementService.UpdateEtablissement(id,requestEtablissement);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "update success !!!"));
  }

    @GetMapping("/{etablissementId}/departements")
    @PreAuthorize("hasAnyRole('Admin', 'USER')")
    public ResponseEntity<List<Departement>> getDepartementsByEtablissementId(@PathVariable Long etablissementId) {
        List<Departement> departements = etablissementService.getDepartementsByEtablissementId(etablissementId);
        if(departements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departements);
    }
}
