package uz.pdp.appspringapitask2codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.emtity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    boolean existsByName(String name);
}
