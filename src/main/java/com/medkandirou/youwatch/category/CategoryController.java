package com.medkandirou.youwatch.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path="api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategorie iCategorie;

    @GetMapping
    public ResponseEntity<List<CategoryDTOres>> getAll(){
        return new ResponseEntity<>(iCategorie.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTOres> save(@Valid @RequestBody CategoryDTOreq categoryDTOreq){
        return new ResponseEntity<>(iCategorie.save(categoryDTOreq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDTOres> update(@Valid @RequestBody CategoryDTOreq categorieId){
        return new ResponseEntity<>(iCategorie.save(categorieId), HttpStatus.OK);
    }

    @GetMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOres> findById(@PathVariable("categorieId") Integer categorieId){
        return new ResponseEntity<>(iCategorie.findById(categorieId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOreq> deleteById(@PathVariable("categorieId") Integer categorieId){
        return new ResponseEntity<>(iCategorie.deleteById(categorieId), HttpStatus.OK);
    }
}
