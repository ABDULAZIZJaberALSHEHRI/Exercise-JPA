package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.CategoryID;
import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Repository.CategoryIdRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final CategoryIdService categoryIdService;


    private final ProductRepository productRepository;
    private final CategoryIdRepository categoryIdRepository;

    public List<Product> GetAllProducts() {

        return productRepository.findAll();
    }


    public boolean addProduct(Product product) {
        CategoryID c = categoryIdRepository.getReferenceById(product.getCategoryID());
        if (c.equals(null)) {
            return false;
        }
        productRepository.save(product);
        return true;
    }

    public boolean removeProduct(Integer id) {
        Product p = productRepository.getById(id);
        if (p == null) {
            return false;
        }
        productRepository.delete(p);
        return true;
    }


    public boolean updateProduct(Integer id,Product product) {

        Product p = productRepository.getById(id);
        if (p == null) {
            return false;
        }
        p.setName(product.getName());
        p.setCategoryID(product.getCategoryID());
        p.setPrice(product.getPrice());
        p.setOffer(product.getOffer());
        productRepository.save(p);
        return true;
    }


}
