package uz.pdp.appspringapitask2codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appspringapitask2codingbat.emtity.Category;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    private Integer categoryId;;
}
