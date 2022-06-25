package uz.pdp.appspringapitask2codingbat.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;


    private Integer questionId;


    private Integer usersId;
}
