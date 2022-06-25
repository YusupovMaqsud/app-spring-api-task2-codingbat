package uz.pdp.appspringapitask2codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.emtity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
}
