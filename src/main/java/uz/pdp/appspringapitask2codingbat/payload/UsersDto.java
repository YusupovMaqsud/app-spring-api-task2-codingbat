package uz.pdp.appspringapitask2codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    @NotNull(message = "email bo'sh bo'lmasligi kerak")
    private String email;

    @NotNull(message = "password bo'sh bo'lmasligi kerak")
    private String password;
}
