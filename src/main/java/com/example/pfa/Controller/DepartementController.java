package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestDepartement;
import com.example.pfa.Dto.ResponseDepartement;
import com.example.pfa.Service.DepartementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/Departements")
@RequiredArgsConstructor
public class DepartementController {
    @Autowired
    private DepartementService departementService ;
    //tab3a http requete

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin','USER')")
    public ResponseEntity<List<ResponseDepartement>> getAllDepartment(){
        List<ResponseDepartement> departements =departementService.getAllDepartement();
        return ResponseEntity.ok(departements);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ResponseDepartement> getDepartementById(@PathVariable Long id){
        return ResponseEntity.ok(departementService.getDepartementById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> addDepartement(@RequestBody @Valid RequestDepartement requestDepartement){
        // type  retour ResponseEntity bsh baad l front inajm yakraha
        departementService.CreateDepartement(requestDepartement);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","save success !"));
    }


    @PutMapping(value = "{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> UpdateDepartement (@PathVariable(name = "id") Long id, @RequestBody @Valid RequestDepartement request)
    {
        departementService.UpdateDepartement(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "update success !!!"));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> DeleteDepartement (@PathVariable Long id ){
        boolean deletedDepartement = departementService.DeleteDepartement(id);
        if(deletedDepartement){
            return ResponseEntity.status(HttpStatus.OK).body( Collections.singletonMap("message"," delete success !!!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message","departement does not exist"));
    }


}
