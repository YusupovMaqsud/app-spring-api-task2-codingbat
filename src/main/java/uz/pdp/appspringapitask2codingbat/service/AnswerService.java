package uz.pdp.appspringapitask2codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appspringapitask2codingbat.emtity.Answer;
import uz.pdp.appspringapitask2codingbat.emtity.Question;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.AnswerDto;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.repository.AnswerRepository;
import uz.pdp.appspringapitask2codingbat.repository.QuestionRepository;
import uz.pdp.appspringapitask2codingbat.repository.UsersRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UsersRepository usersRepository;


    /**
     * BARCH ANSWERLARNI QAYTARADI
     * @return
     */
    public List<Answer> getAnswers() {
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }


    /**
     * ID BO'YICH ANSWER
     * @param id
     * @return
     */
    public Answer getAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            return optionalAnswer.get();
        }
        return null;
    }



    /**
     * ANSWER QUSHISH
     * @param answerDto
     * @return
     */
    public ApiResponse addAnswer(@Valid @RequestBody AnswerDto answerDto) {
        boolean existsByName = answerRepository.existsByName(answerDto.getName());
        if (existsByName) {
            return new ApiResponse("Bunday answer nameli mavjud", false);
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (!optionalQuestion.isPresent()) {
            return new ApiResponse("Bunday question mavjud emas", false);
        }

        Optional<Users> optionalUsers = usersRepository.findById(answerDto.getUsersId());
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Bunday user mavjud emas", false);
        }


        Answer answer = new Answer();
        answer.setName(answerDto.getName());
        answer.setQuestion(optionalQuestion.get());
        answer.setUsers(optionalUsers.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer saqlandi", true);
    }




    /**
     * ANSWER O'CHIRISH
     * @param id
     * @return
     */
    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik!!!", false);
        }
    }




    /**
     * ANSWERNI O'CHIRISH
     * @param answerDto
     * @param id
     * @return
     */
    public ApiResponse editAnswer(@Valid @RequestBody AnswerDto answerDto, @PathVariable Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if(!optionalAnswer.isPresent()){
            return new ApiResponse("Bunday Answer mavjud emas",false);
        }

        Optional<Question> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (!optionalQuestion.isPresent()) {
            return new ApiResponse("Bunday question mavjud emas", false);
        }

        Optional<Users> optionalUsers = usersRepository.findById(answerDto.getUsersId());
        if (!optionalUsers.isPresent()) {
            return new ApiResponse("Bunday user mavjud emas", false);
        }


        Answer answer = optionalAnswer.get();
        answer.setName(answerDto.getName());
        answer.setQuestion(optionalQuestion.get());
        answer.setUsers(optionalUsers.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer edited", true);
    }
}
