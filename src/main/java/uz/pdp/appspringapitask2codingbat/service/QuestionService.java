package uz.pdp.appspringapitask2codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appspringapitask2codingbat.emtity.Category;
import uz.pdp.appspringapitask2codingbat.emtity.Language;
import uz.pdp.appspringapitask2codingbat.emtity.Question;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.LanguageDto;
import uz.pdp.appspringapitask2codingbat.payload.QuestionDto;
import uz.pdp.appspringapitask2codingbat.repository.CategoryRepository;
import uz.pdp.appspringapitask2codingbat.repository.LanguageRepository;
import uz.pdp.appspringapitask2codingbat.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CategoryRepository categoryRepository;


    /**
     * BARCHA QUESTIONLARNI QAYTARISH
     *
     * @return
     */
    public List<Question> getQuestions() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }


    /**
     * ID ORQALI QUESTION
     *
     * @param id
     * @return
     */
    public Question getQuestion(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        }
        return null;
    }


    /**
     * QUESTION QUSHADIGAN
     *
     * @param
     * @return
     */
    public ApiResponse addQuestion(QuestionDto questionDto) {
        boolean existsByName = questionRepository.existsByName(questionDto.getName());
        if (existsByName) {
            return new ApiResponse("Bunday Question nameli mavjud", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(questionDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday category mavjud emas ",false);
        }

        Question question=new Question();
        question.setName(questionDto.getName());
        question.setCategory(optionalCategory.get());
        questionRepository.save(question);
        return new ApiResponse("Question saqlandi", true);
    }


    /**
     * QUESTION O'CHRISH
     *
     * @param id
     * @return
     */
    public ApiResponse deleteQuestion(Integer id) {
        try {
            questionRepository.deleteById(id);
            return new ApiResponse("Question o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik!!!", false);

        }
    }


    /**
     * QUESTION EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    public ApiResponse editQuestion(QuestionDto questionDto, Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            return new ApiResponse("Bunday Question mavjud emas", false);
        }


        Optional<Category> optionalCategory = categoryRepository.findById(questionDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ApiResponse("Bunday category mavjud emas ",false);
        }

        Question question=new Question();
        question.setName(questionDto.getName());
        question.setCategory(optionalCategory.get());
        questionRepository.save(question);

        return new ApiResponse("Question edited", true);
    }
}
