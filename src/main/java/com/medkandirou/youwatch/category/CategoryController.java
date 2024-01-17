package com.medkandirou.youwatch.category;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path="api/categorie")
public class CategoryController {

    private final CategorieService CategorieService;

    private CategoryController(CategorieService CategorieService){
        this.CategorieService=CategorieService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTOres>> getAll(){
        return new ResponseEntity<>(CategorieService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTOres> save(@Valid @RequestBody CategoryDTOreq categorieId){
        return new ResponseEntity<>(CategorieService.save(categorieId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDTOres> update(@Valid @RequestBody CategoryDTOreq categorieId){
        return new ResponseEntity<>(CategorieService.save(categorieId), HttpStatus.OK);
    }

    @GetMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOres> findById(@PathVariable("categorieId") Long categorieId){
        return new ResponseEntity<>(CategorieService.findById(categorieId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOreq> deleteById(@PathVariable("categorieId") Long categorieId){
        return new ResponseEntity<>(CategorieService.deleteById(categorieId), HttpStatus.OK);
    }
}
