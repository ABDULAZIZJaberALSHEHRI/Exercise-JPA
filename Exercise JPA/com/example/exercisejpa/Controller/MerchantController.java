package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get-merchants")
    public ResponseEntity getMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add-merchant")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(201).body("Merchant added successfully");
    }

    @PutMapping("/update-merchant/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id,@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = merchantService.updateMerchant(id,merchant);
        if (isUpdated) {
            return ResponseEntity.status(201).body("Merchant updated successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found");
    }

    @DeleteMapping("/del-merchant/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body("Merchant deleted successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found");
    }


}
