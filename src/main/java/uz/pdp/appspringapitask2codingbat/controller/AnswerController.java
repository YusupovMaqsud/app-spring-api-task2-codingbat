package uz.pdp.appspringapitask2codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.payload.AnswerDto;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.service.AnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnswerController {
    @Autowired
    AnswerService answerService;

    /**
     * BARCH ANSWERLARNI QAYTARADI
     *
     * @return
     */
    @GetMapping("/api/answer")
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.getAnswers();
        return ResponseEntity.ok(answers);
    }


    /**
     * ID BO'YICH ANSWER
     *
     * @param id
     * @return
     */
    @GetMapping("/api/answer/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Integer id) {
        Answer answer = answerService.getAnswer(id);
        return ResponseEntity.ok(answer);
    }


    /**
     * ANSWER QUSHISH
     *
     * @param answerDto
     * @return
     */
    @PostMapping("/api/answer")
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * ANSWER O'CHIRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/answer/{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id) {
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * ANSWERNI O'CHIRISH
     *
     * @param answerDto
     * @param id
     * @return
     */
    @PutMapping("/api/answer/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@Valid @RequestBody AnswerDto answerDto, @PathVariable Integer id) {
        ApiResponse apiResponse = answerService.editAnswer(answerDto, id);
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
