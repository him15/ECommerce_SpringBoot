package com.ecommerce.project.controller;


import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/public/categories/")
    public  ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER , required = false) Integer pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize ,
            @RequestParam(name="sortBy", defaultValue = AppConstants.Sort_Categories_By, required = false) String sortBy,
            @RequestParam(name="sortOrder", defaultValue = AppConstants.Sort_DIR, required = false) String sortOrder

        ) {

        CategoryResponse categoryResponse=  categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse , HttpStatus.OK); // it is directly converting list in the json format with the help of jackson.
    }


    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO =  categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {

            CategoryDTO deletedCategoryDTO =  categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategoryDTO , HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Long categoryId) {
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO , categoryId);
            return new ResponseEntity<>(savedCategoryDTO , HttpStatus.OK);

    }
}
