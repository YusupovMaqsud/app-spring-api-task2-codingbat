package uz.pdp.appspringapitask2codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appspringapitask2codingbat.emtity.Category;
import uz.pdp.appspringapitask2codingbat.emtity.Language;
import uz.pdp.appspringapitask2codingbat.emtity.Users;
import uz.pdp.appspringapitask2codingbat.payload.ApiResponse;
import uz.pdp.appspringapitask2codingbat.payload.CategoryDto;
import uz.pdp.appspringapitask2codingbat.payload.UsersDto;
import uz.pdp.appspringapitask2codingbat.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;



    /**
     * BARCHA CATEGORY QAYTARISH
     *
     * @return
     */
    public List<Category> getCategoryes() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }


    /**
     * ID ORQALI CATEGORY
     *
     * @param id
     * @return
     */
    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        return null;
    }


    /**
     * CATEGORY QUSHADIGAN
     *
     * @param
     * @return
     */
    public ApiResponse addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) {
            return new ApiResponse("Bunday Category nameli mavjud", false);
        }


        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if(!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language mavjud emas",false);
        }


        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setLanguage(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Category saqlandi", true);
    }


    /**
     * CATEGORY O'CHRISH
     *
     * @param id
     * @return
     */
    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik!!!", false);

        }
    }


    /**
     * CATEGORYNI EDIT QILISH
     *
     * @param
     * @param id
     * @return
     */
    public ApiResponse editCategory(CategoryDto categoryDto, Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Bunday Category mavjud emas", false);
        }

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if(!optionalLanguage.isPresent()){
            return new ApiResponse("Bunday language mavjud emas",false);
        }


        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setLanguage(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Category edited", true);
    }


}
