package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-products")
    public ResponseEntity getProducts() {
        return ResponseEntity.status(200).body(productService.GetAllProducts());
    }

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded =productService.addProduct(product);
        if (isAdded){
            return ResponseEntity.status(201).body("product added successfully");
        }
        return ResponseEntity.status(400).body("product not added successfully because category id not found");
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean updated = productService.updateProduct(id,product);
        if (updated) {
            return ResponseEntity.status(201).body("product updated successfully");
        }
        return ResponseEntity.status(400).body("product not found");

    }

    @DeleteMapping("/del-product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productService.removeProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body("product deleted successfully");
        }
        return ResponseEntity.status(400).body("product not found");
    }
}
