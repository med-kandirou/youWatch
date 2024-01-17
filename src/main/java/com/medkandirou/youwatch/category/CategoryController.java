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

    private final CategoryService categoryService;

    private CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTOres>> getAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTOres> save(@Valid @RequestBody CategoryDTOreq categorieId){
        return new ResponseEntity<>(categoryService.save(categorieId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDTOres> update(@Valid @RequestBody CategoryDTOreq categorieId){
        return new ResponseEntity<>(categoryService.save(categorieId), HttpStatus.OK);
    }

    @GetMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOres> findById(@PathVariable("categorieId") Long categorieId){
        return new ResponseEntity<>(categoryService.findById(categorieId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{categorieId}"})
    public ResponseEntity<CategoryDTOreq> deleteById(@PathVariable("categorieId") Long categorieId){
        return new ResponseEntity<>(categoryService.deleteById(categorieId), HttpStatus.OK);
    }
}
