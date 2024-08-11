package com.example.exercisejpa.Service;


import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Repository.MerchantRepository;
import com.example.exercisejpa.Repository.MerchantStockRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final ProductService productService;
    private final MerchantService merchantService;


    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;

    public boolean add(MerchantStock merchantStock) {
        Product p = productRepository.getReferenceById(merchantStock.getProductID());
        Merchant m = merchantRepository.getReferenceById(merchantStock.getMerchantID());
        if (p.equals(null) || m.equals(null)) {
                return false;
        }
        merchantStockRepository.save(merchantStock);
        return true;

    }

    public List<MerchantStock> getMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public boolean remove(Integer id) {
        MerchantStock m = merchantStockRepository.getById(id);
        if (m == null) {
            return false;
        }
        merchantStockRepository.delete(m);
        return true;
    }

    public boolean update(Integer id, MerchantStock merchantStock) {
        MerchantStock m = merchantStockRepository.getById(id);
        if (m == null) {
            return false;
        }
        merchantStock.setId(m.getId());
        m.setMerchantID(merchantStock.getMerchantID());
        m.setProductID(merchantStock.getProductID());
        m.setStock(merchantStock.getStock());
        merchantStockRepository.save(m);
        return true;
    }

    //in this method user "merchant" can update product stock value by enter merchant stock id and the amount
    public boolean updateStocks(Integer merchantStockID,int amount) {
    MerchantStock m = merchantStockRepository.getById(merchantStockID);

            if (m.equals(null)) {
               return false;
            }
        m.setStock(amount);
        merchantStockRepository.save(m);
        return true;
    }


}
