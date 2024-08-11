package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.CategoryID;
import com.example.exercisejpa.Service.CategoryIdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/categoryid")
@RequiredArgsConstructor
public class CategoryIdController {
    private final CategoryIdService categoryService;

    @RequestMapping("/add-categoryid")
    public ResponseEntity addCategoryId(@Valid @RequestBody CategoryID categoryID, Errors errors) {
        if (errors.hasErrors()){
            String message =errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategoryID(categoryID);
        return ResponseEntity.status(200).body("CategoryID added successfully");
    }

    @RequestMapping("/get-categoryid")
    public ResponseEntity getCategoryId() {

        return ResponseEntity.status(200).body(categoryService.getCategoryIds());
    }

    @RequestMapping("/remove/categoryid/{id}")
    public ResponseEntity removeCategoryId(@PathVariable Integer id) {
        boolean isRemoved = categoryService.remove(id);
        if (isRemoved){
            return ResponseEntity.status(200).body("CategoryID removed successfully");
        }
        return ResponseEntity.status(400).body("CategoryID not found");
    }

    @RequestMapping("/update-categoryid/{id}")
    public ResponseEntity updateCategoryid(@PathVariable Integer id, @Valid @RequestBody CategoryID categoryID, Errors errors) {
        if (errors.hasErrors()){
            String message =errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = categoryService.update(id, categoryID);
        if (isUpdated){
            return ResponseEntity.status(200).body("CategoryID Updated successfully");
        }
        return ResponseEntity.status(400).body("CategoryID not found");
    }

}
