package uz.pdp.appspringapitask2codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.repository.CategoryRepository;
import uz.pdp.appspringapitask2codingbat.repository.LanguageRepository;
import uz.pdp.appspringapitask2codingbat.repository.UsersRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    /**
     * BARCHA USERLARNI QAYTARISH
     *
     * @return userList
     */
    public List<Users> getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersList;
    }


    /**
     * ID ORQALI USER
     *
     * @param id
     * @return USER
     */
    public Users getUser(Integer id) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        if (usersOptional.isPresent()) {
            return usersOptional.get();
        }
        return null;
    }


    /**
     * USER QUSHADIGAN
     *
     * @param usersDto
     * @return
     */
    public ApiResponse addUsers(UsersDto usersDto) {
        boolean existsByEmail = usersRepository.existsByEmail(usersDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Bunday User emailli mavjud", false);
        }

        Users users = new Users();
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
        return new ApiResponse("Users saqlandi", true);
    }


    /**
     * USER O'CHRISH
     *
     * @param id
     * @return
     */
    public ApiResponse deleteUsers(Integer id) {
        try {
            usersRepository.deleteById(id);
            return new ApiResponse("User o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik!!!", false);

        }
    }


    /**
     * USERNI EDIT QILISH
     *
     * @param usersDto
     * @param id
     * @return
     */
    public ApiResponse editUsers(UsersDto usersDto, Integer id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Bunday user mavjud emas", false);
        }

        Users users = optionalUsers.get();
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
        return new ApiResponse("Users edited", true);
    }


}
