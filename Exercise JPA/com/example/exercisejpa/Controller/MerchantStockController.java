package com.example.exercisejpa.Controller;


import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get-merchant-stock")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add-merchant-stock")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors err) {
        if (err.hasErrors()) {
            String message = err.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdded = merchantStockService.add(merchantStock);
        if (isAdded) {
            return ResponseEntity.status(200).body("merchantStock added successfully");
        }
        return ResponseEntity.status(400).body("Something went wrong in product id or merchant id!");
    }

    @PutMapping("/update-merchant-stock/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @Valid @RequestBody MerchantStock merchantStock, Errors err) {
        if (err.hasErrors()) {
            String message = err.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = merchantStockService.update(id, merchantStock);
        if (isUpdated) {
            return ResponseEntity.status(201).body("Merchant stock updated successfully");
        }
        return ResponseEntity.status(400).body("Merchant stock id is not found");
    }

    @DeleteMapping("/delete-merchant-stock/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        boolean isDeleted=merchantStockService.remove(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body("Merchant stock deleted successfully");
        }
        return ResponseEntity.status(400).body("Merchant stock id is not found");
    }

    //in this ENDPOINT user "merchant" can update product stock value by enter merchant stock id and the amount
    @PutMapping("/update-stock/{merchantStockID}/{amount}")
    public ResponseEntity updateStock(@PathVariable Integer merchantStockID,@PathVariable int amount) {
        boolean isStockUpdated = merchantStockService.updateStocks(merchantStockID,amount);
        if (isStockUpdated) {
            return ResponseEntity.status(201).body("Stock updated successfully");
        }
        return ResponseEntity.status(404).body("Merchant Stock Id id not found");
    }
}
