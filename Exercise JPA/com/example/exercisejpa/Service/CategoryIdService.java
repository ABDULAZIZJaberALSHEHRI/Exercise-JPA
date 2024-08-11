package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.CategoryID;
import com.example.exercisejpa.Repository.CategoryIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryIdService {

    private final CategoryIdRepository categoryIdRepository;


    public List<CategoryID> getCategoryIds() {

        return categoryIdRepository.findAll();
    }


    public void addCategoryID(CategoryID categoryID) {

        categoryIdRepository.save(categoryID);
    }


    public boolean remove(Integer id) {
        CategoryID c = categoryIdRepository.getById(id);
        if (c == null) {
            return false;
        }
        categoryIdRepository.deleteById(id);
        return true;
    }


    public boolean update(Integer id, CategoryID categoryID) {
        CategoryID c = categoryIdRepository.getById(id);
        if (c == null) {
            return false;
        }
        c.setName(categoryID.getName());
        categoryIdRepository.save(c);
        return true;
    }
}
