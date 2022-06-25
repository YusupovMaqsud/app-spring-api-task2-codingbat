package uz.pdp.appspringapitask2codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringapitask2codingbat.emtity.Language;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.LanguageDto;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.service.AnswerService;
import uz.pdp.appspringapitask2codingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LanguageController {
    @Autowired
    LanguageService languageService;


    /**
     * BARCHA LANGUAGELARNI QAYTARISH
     *
     * @return
     */
    @GetMapping("/api/language")
    public ResponseEntity<List<Language>> getLanguages() {
        List<Language> languages = languageService.getLanguages();
        return ResponseEntity.ok(languages);
    }


    /**
     * ID ORQALI LANGUAGE
     *
     * @param id
     * @return
     */
    @GetMapping("/api/language/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Integer id) {
        Language language = languageService.getLanguage(id);
        return ResponseEntity.ok(language);
    }


    /**
     * LANGUAGE QUSHADIGAN
     *
     * @param
     * @return
     */
    @PostMapping("/api/language")
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * LANGUAGE O'CHRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/language/{id}")
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * LANGUAGE EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    @PutMapping("/api/Language/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@Valid @RequestBody LanguageDto languageDto, @PathVariable Integer id) {
        ApiResponse apiResponse = languageService.editLanguage(languageDto, id);
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
