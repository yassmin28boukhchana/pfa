package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestCategorie;
import com.example.pfa.Dto.RequestDepartement;
import com.example.pfa.Dto.ResponseCategorie;
import com.example.pfa.Service.CategorieService;
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
@RequestMapping("api/Categories")
@RequiredArgsConstructor
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategorie> getCategorieById(@PathVariable Long id) {
        ResponseCategorie responseCategorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(responseCategorie);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategorie>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategorie(@PathVariable Long id) {
        boolean deletedCategory = categorieService.deleteCategorie(id);
        if (deletedCategory) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Delete success !!!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Category does not exist"));
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody @Valid RequestCategorie requestCategorie) {
        try {
            categorieService.createCategorie(requestCategorie);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully.");
        } catch (RuntimeException ex) {
            // Gérer ici les exceptions spécifiques comme une catégorie parent non trouvée
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            // Gérer les autres exceptions imprévues
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the category.");
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateCategorie(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestCategorie requestCategorie) {
        categorieService.updateCategorie(id, requestCategorie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Update success !!!"));
    }

}
