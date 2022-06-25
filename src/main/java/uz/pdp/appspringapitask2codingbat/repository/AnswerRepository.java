package uz.pdp.appspringapitask2codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    boolean existsByName(String name);
}
