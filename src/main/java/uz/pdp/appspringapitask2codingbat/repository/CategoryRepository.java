package uz.pdp.appspringapitask2codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.emtity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
