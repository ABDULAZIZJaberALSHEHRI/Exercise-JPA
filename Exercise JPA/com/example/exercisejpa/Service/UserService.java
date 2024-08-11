package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.*;
import com.example.exercisejpa.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;
    private final MerchantStockService merchantStockService;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final ReviewRepository reviewRepository;
    private final CartRepository cartRepository;

    public void addUser(User user){

        userRepository.save(user);
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }


    public boolean deleteUser(Integer id) {
        User u = userRepository.getById(id);
        if(u == null){
            return false;
        }
        userRepository.delete(u);
        return true;
    }

    public boolean updateUser(Integer id,User user) {
        User u = userRepository.getById(id);
        if(u == null){
            return false;
        }
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setBalance(user.getBalance());
        userRepository.save(u);
        return true;
    }


    public boolean buyProduct(int userID, int productID, int merchantID){

        User u = userRepository.getReferenceById(userID);
        Product p = productRepository.getReferenceById(productID);
        Merchant m = merchantRepository.getReferenceById(merchantID);
        MerchantStock ms = merchantStockRepository.findByProductID(productID);
        if (u.equals(null) || p.equals(null) || m.equals(null) || ms.equals(null)){
            return false;
        }


                if (u.getRole().equalsIgnoreCase("Customer")) {
                    if (p.getPrice() <= u.getBalance()) {
                        u.setBalance(u.getBalance() - p.getPrice());
                        ms.setStock(ms.getStock()-1);
                        merchantStockRepository.save(ms);
                        userRepository.save(u);
                        return true;
                    }
                }
        return false;
    }


    //this method will display to the user all products that share the same category by input the product id
    public List<Product> getSimilarProducts(int productID) {
        List<Product> sameCategories = new ArrayList<>(List.of());

        for (int i = 0; i < productService.GetAllProducts().size(); i++) {
            if (productService.GetAllProducts().get(i).getId() == productID) {
                for (int j = 0; j < productService.GetAllProducts().size(); j++) {
                    if (productService.GetAllProducts().get(i).getCategoryID() ==productService.GetAllProducts().get(j).getCategoryID()) {
                        sameCategories.add(productService.GetAllProducts().get(j));
                    }
                }
            }
        }
        if (sameCategories.isEmpty()) {
            return null;
        }
        return sameCategories;
    }


    //user can add review by including his id and product id and the review
    public boolean addReview(Reviews reviews) {
        User u = userRepository.getReferenceById(reviews.getUserID());
        Product p = productRepository.getReferenceById(reviews.getProductID());
        if (u.equals(null) || p.equals(null)){
            return false;
        }
        reviewRepository.save(reviews);
        return true;
    }


    //user can add multiple products to his cart
    public boolean addToCart(int userID, int productID, int merchantID) {
        User u = userRepository.getReferenceById(userID);
        Product p = productRepository.getReferenceById(productID);
        Merchant m = merchantRepository.getReferenceById(merchantID);
        MerchantStock ms = merchantStockRepository.findByProductID(productID);
        Cart cart = new Cart();
        if (u.equals(null) || p.equals(null) || m.equals(null) || ms.equals(null)){
            return false;
        }
        if (u.getRole().equalsIgnoreCase("Customer")) {
            if(ms.getStock()>0){
                cart.setUserID(u.getId());
                cart.setProductID(p.getId());
                cart.setName(p.getName());
                cart.setPrice(p.getPrice());
                cart.setCategoryID(p.getCategoryID());
                cart.setOffer(p.getOffer());
                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }


    //user after add the products in his cart, by this method he can complete cart purchase
        public boolean completeCartPurchase(int userid){
        int total = 0;
        int productWithOfferCounter=0;

        User u = userRepository.getReferenceById(userid);
        if (u.equals(null)){
            return false;
        }

                for (int j = 0; j < cartRepository.findAll().size(); j++) {
                    if (cartRepository.findAll().get(j).getOffer().equalsIgnoreCase("two for one price")) {
                        productWithOfferCounter++;
                        if (productWithOfferCounter >= 2) {
                            total = (cartRepository.findAll().get(j).getPrice() + total) / 2;
                        }else {
                            total = cartRepository.findAll().get(j).getPrice() + total;
                        }

                    }else {
                        total = cartRepository.findAll().get(j).getPrice() + total;
                    }

                }
                if (u.getBalance() > total) {
                    u.setBalance(u.getBalance() - total);
                    cartRepository.deleteById(cartRepository.getReferenceById(userid).getId());
                    return true;
                }


        return false;
    }


    //user can delete product from cart by pass user id and cart id
    public boolean deleteFromCart(int userid, int cartID) {
        User u = userRepository.getReferenceById(userid);
        Cart cart = cartRepository.getReferenceById(cartID);
        if (u.equals(null) || cart.equals(null)){
            return false;
        }
        cartRepository.delete(cart);
        return true;
    }

}
