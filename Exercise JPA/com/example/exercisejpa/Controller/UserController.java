package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.Reviews;
import com.example.exercisejpa.Model.User;
import com.example.exercisejpa.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add-user")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(400).body("User added successfully");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(200).body("User updated successfully");
        }
        return ResponseEntity.status(400).body("User not exists");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("User deleted successfully");
        }
        return ResponseEntity.status(400).body("User not exists");
    }

    @PutMapping("/buy-product/{userid}/{productid}/{merchantid}")
    public ResponseEntity buyProduct(@PathVariable int userid, @PathVariable int productid, @PathVariable int merchantid) {
        boolean isBuy = userService.buyProduct(userid, productid, merchantid);
        if (isBuy) {
            return ResponseEntity.status(200).body("User buy product successfully");
        }

        return ResponseEntity.status(400).body("buy not completed successfully");
    }

    @GetMapping("/same-products/{productid}")
    public ResponseEntity sameProducts(@PathVariable int productid) {
        if (userService.getSimilarProducts(productid) == null){
            return ResponseEntity.status(400).body("Product not exists");
        }
        return ResponseEntity.status(200).body(userService.getSimilarProducts(productid));
    }

    @PutMapping("/add-review")
    public ResponseEntity addReview(@RequestBody Reviews review) {
        boolean isReviewd = userService.addReview(review);
        if (isReviewd) {
            return ResponseEntity.status(200).body("Review added successfully");
        }
        return ResponseEntity.status(400).body("Error occurred, please ensure that user id AND product id is correct.");
    }

    @PutMapping("/add-product-to-cart/{userid}/{productid}/{merchantid}")
    public ResponseEntity addProductToCart(@PathVariable int userid, @PathVariable int productid, @PathVariable int merchantid) {
        boolean isAddedToCart = userService.addToCart(userid,productid,merchantid);
        if (isAddedToCart) {
            return ResponseEntity.status(200).body("Product added to cart successfully");
        }
        return ResponseEntity.status(400).body("some thing went wrong");
    }


    @PutMapping("/complete-purchase/{userid}")
    public ResponseEntity completePurchase(@PathVariable int userid) {
        boolean isPurchaseCompleted = userService.completeCartPurchase(userid);
        if (isPurchaseCompleted) {
            return ResponseEntity.status(200).body("Purchase completed successfully");
        }
        return ResponseEntity.status(400).body("Error occurred while completing the purchase, please ensure that user id " +
                "is correct AND your balance is more than total products price.");
    }



    @DeleteMapping("/del-from-cart/{userid}/{cartId}")
    public ResponseEntity delFromCart(@PathVariable int userid, @PathVariable int cartId) {
        boolean isDel = userService.deleteFromCart(userid, cartId);
        if (isDel) {
            return ResponseEntity.status(200).body("Product deleted successfully");
        }
        return ResponseEntity.status(400).body("Error occurred while deleting from the cart, please ensure that user id " +
                "is correct AND product id is correct.");
    }
}
