package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestBien;
import com.example.pfa.Dto.ResponseBien;
import com.example.pfa.Service.BienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/Biens")
@RequiredArgsConstructor
public class BienController {
    @Autowired
    private BienService bienService ;

    @GetMapping
    public ResponseEntity<List<ResponseBien>> getAllBiens(){
        List <ResponseBien> biens = bienService.getAllBiens();
        return ResponseEntity.ok(biens) ;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseBien> getBienById(@PathVariable Long id){
        return ResponseEntity.ok(bienService.getBienById(id)) ;
    }

    @PostMapping
    public ResponseEntity<Object> createBien(@RequestBody @Valid RequestBien requestBien){
        bienService.createBien(requestBien);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","save success !"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBien ( @PathVariable  Long id){
        Boolean deletedbien = bienService.deleteBien(id) ;
        if(deletedbien){
            return ResponseEntity.status(HttpStatus.OK).body( Collections.singletonMap("message"," delete success !!!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message","bien does not exist"));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBien(@PathVariable Long id , @RequestBody @Valid RequestBien requestBien){
        bienService.UpdateBien(id,requestBien);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "update success !!!"));
    }

}
