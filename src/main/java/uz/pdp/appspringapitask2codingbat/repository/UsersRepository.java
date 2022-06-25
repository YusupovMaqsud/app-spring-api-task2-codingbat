package uz.pdp.appspringapitask2codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.emtity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    boolean existsByEmail(String email);
}
