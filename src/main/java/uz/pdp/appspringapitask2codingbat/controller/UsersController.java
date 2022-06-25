package uz.pdp.appspringapitask2codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.service.AnswerService;
import uz.pdp.appspringapitask2codingbat.service.UsersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    /**
     * BARCHA USERLARNI QAYTARISH
     *
     * @return userList
     */
    @GetMapping("/api/users")
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> usersList = usersService.getUsers();
        return ResponseEntity.ok(usersList);
    }


    /**
     * ID ORQALI USER
     *
     * @param id
     * @return USER
     */
    @GetMapping("/api/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Integer id) {
        Users user = usersService.getUser(id);
        return ResponseEntity.ok(user);
    }


    /**
     * USER QUSHADIGAN
     *
     * @param usersDto
     * @return
     */
    @PostMapping("/api/users")
    public ResponseEntity<ApiResponse> addUsers(@Valid @RequestBody UsersDto usersDto) {
        ApiResponse apiResponse = usersService.addUsers(usersDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * USER O'CHRISH
     *
     * @param id
     * @return
     */
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable Integer id) {
        ApiResponse apiResponse = usersService.deleteUsers(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * USERNI EDIT QILISH
     *
     * @param usersDto
     * @param id
     * @return
     */
    @PutMapping("/api/users/{id}")
    public ResponseEntity<ApiResponse> editUsers(@Valid @RequestBody UsersDto usersDto, @PathVariable Integer id) {
        ApiResponse apiResponse = usersService.editUsers(usersDto, id);
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
