package com.medkandirou.youwatch.category;

import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategorie{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository CategoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = CategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTOres findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie : " + id));
        return modelMapper.map(category, CategoryDTOres.class);
    }

    @Override
    public List<CategoryDTOres> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(cat -> modelMapper.map(cat, CategoryDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOres save(CategoryDTOreq entity) {
        Category category = modelMapper.map(entity, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDTOres.class);
    }

    @Override
    public CategoryDTOres update(CategoryDTOreq entity) {
        return null;
    }

    @Override
    public CategoryDTOreq deleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id Categorie: " + id));
        categoryRepository.deleteById(id);
        return modelMapper.map(category, CategoryDTOreq.class);
    }
}
