package uz.pdp.appspringapitask2codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appspringapitask2codingbat.emtity.Language;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.LanguageDto;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.repository.AnswerRepository;
import uz.pdp.appspringapitask2codingbat.repository.LanguageRepository;
import uz.pdp.appspringapitask2codingbat.repository.QuestionRepository;
import uz.pdp.appspringapitask2codingbat.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;



    /**
     * BARCHA LANGUAGELARNI QAYTARISH
     *
     * @return languageList
     */
    public List<Language> getLanguages() {
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }


    /**
     * ID ORQALI LANGUAGE
     *
     * @param id
     * @return
     */
    public Language getLanguage(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            return optionalLanguage.get();
        }
        return null;
    }


    /**
     * LANGUAGE QUSHADIGAN
     *
     * @param languageDto
     * @return
     */
    public ApiResponse addLanguage(LanguageDto languageDto) {
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName) {
            return new ApiResponse("Bunday Language nameli mavjud", false);
        }

        Language language=new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language saqlandi", true);
    }


    /**
     * LANGUAGE O'CHRISH
     *
     * @param id
     * @return
     */
    public ApiResponse deleteLanguage(Integer id) {
        try {
           languageRepository.deleteById(id);
            return new ApiResponse("Language o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik!!!", false);

        }
    }


    /**
     * LANGUAGENI EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    public ApiResponse editLanguage(LanguageDto languageDto, Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()) {
            return new ApiResponse("Bunday Language mavjud emas", false);
        }

        Language language=new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language edited", true);
    }

}
