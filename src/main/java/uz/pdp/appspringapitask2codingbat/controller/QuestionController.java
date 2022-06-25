package uz.pdp.appspringapitask2codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringapitask2codingbat.emtity.Language;
import uz.pdp.appspringapitask2codingbat.emtity.Question;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.LanguageDto;
import uz.pdp.appspringapitask2codingbat.payload.QuestionDto;
import uz.pdp.appspringapitask2codingbat.service.AnswerService;
import uz.pdp.appspringapitask2codingbat.service.QuestionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;


    /**
     * BARCHA QUESTIONLARNI QAYTARISH
     *
     * @return
     */
    @GetMapping("/api/question")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getQuestions();
        return ResponseEntity.ok(questions);
    }


    /**
     * ID ORQALI QUESTION
     *
     * @param id
     * @return
     */
    @GetMapping("/api/question/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Integer id) {
        Question question = questionService.getQuestion(id);
        return ResponseEntity.ok(question);
    }



    /**
     * QUESTION QUSHADIGAN
     *
     * @param
     * @return
     */
    @PostMapping("/api/question")
    public ResponseEntity<ApiResponse> addQuestion(@Valid @RequestBody QuestionDto questionDto) {
        ApiResponse apiResponse = questionService.addQuestion(questionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * QUESTION O'CHRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/question/{id}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Integer id) {
        ApiResponse apiResponse = questionService.deleteQuestion(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * QUESTION EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    @PutMapping("/api/question/{id}")
    public ResponseEntity<ApiResponse> editQuestion(@Valid @RequestBody QuestionDto questionDto, @PathVariable Integer id) {
        ApiResponse apiResponse = questionService.editQuestion(questionDto, id);
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
