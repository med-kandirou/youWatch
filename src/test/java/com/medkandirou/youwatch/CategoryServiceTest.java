package com.medkandirou.youwatch;

import com.medkandirou.youwatch.category.*;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void findById_existingId_categoryFound() {
        // Arrange
        Integer id = 1;
        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDTOres.class)).thenReturn(new CategoryDTOres());

        // Act
        CategoryDTOres result = categoryService.findById(id);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).findById(id);
        verify(modelMapper).map(category, CategoryDTOres.class);
    }

    @Test
    void findById_nonExistingId_resourceNotFoundExceptionThrown() {
        // Arrange
        Integer id = 1;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class, () -> categoryService.findById(id));
        verify(categoryRepository).findById(id);
        verifyNoInteractions(modelMapper);
    }

    @Test
    void findAll_existingCategories_returnListOfCategories() {
        // Arrange
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        when(modelMapper.map(any(), eq(CategoryDTOres.class))).thenReturn(new CategoryDTOres());

        // Act
        List<CategoryDTOres> result = categoryService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        verify(categoryRepository).findAll();
        verify(modelMapper, times(categories.size())).map(any(), eq(CategoryDTOres.class));
    }

    @Test
    void save_validCategoryDTO_entitySavedAndMappedToDTO() {
        // Arrange
        CategoryDTOreq categoryDTOreq = new CategoryDTOreq();
        Category category = new Category();
        when(modelMapper.map(categoryDTOreq, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(modelMapper.map(category, CategoryDTOres.class)).thenReturn(new CategoryDTOres());

        // Act
        CategoryDTOres result = categoryService.save(categoryDTOreq);

        // Assert
        assertNotNull(result);
        verify(modelMapper).map(categoryDTOreq, Category.class);
        verify(categoryRepository).save(category);
        verify(modelMapper).map(category, CategoryDTOres.class);
    }

    @Test
    void deleteById_existingId_categoryDeletedAndMappedToDTO() {
        // Arrange
        Integer id = 1;
        Category category = new Category();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(id);
        when(modelMapper.map(category, CategoryDTOreq.class)).thenReturn(new CategoryDTOreq());

        // Act
        CategoryDTOreq result = categoryService.deleteById(id);

        // Assert
        assertNotNull(result);
        verify(categoryRepository).findById(id);
        verify(categoryRepository).deleteById(id);
        verify(modelMapper).map(category, CategoryDTOreq.class);
    }

    @Test
    void deleteById_nonExistingId_resourceNotFoundExceptionThrown() {
        Integer id = 1;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> categoryService.deleteById(id));
        verify(categoryRepository).findById(id);
        verifyNoInteractions(modelMapper);
    }
}
