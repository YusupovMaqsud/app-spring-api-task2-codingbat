package uz.pdp.appspringapitask2codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringapitask2codingbat.emtity.Category;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.CategoryDto;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.service.AnswerService;
import uz.pdp.appspringapitask2codingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    /**
     * BARCHA CATEGORY QAYTARISH
     *
     * @return
     */
    @GetMapping("/api/category")
    public ResponseEntity<List<Category>> getCategoryes() {
        List<Category> categoryList = categoryService.getCategoryes();
        return ResponseEntity.ok(categoryList);
    }


    /**
     * ID ORQALI CATEGORY
     *
     * @param id
     * @return
     */
    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) {
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }


    /**
     * CATEGORY QUSHADIGAN
     *
     * @param
     * @return
     */
    @PostMapping("/api/category")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * CATEGORY O'CHRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * CATEGORYNI EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    @PutMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.editCategory(categoryDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
