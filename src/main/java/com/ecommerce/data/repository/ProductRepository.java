package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.ProductException;
import com.ecommerce.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public default Product saveProduct(Product product) throws ProductException {
        if(!productHasName(product)){
            throw new ProductException("product must have a name");
        }else if(!productHasDescription(product)){
            throw new ProductException("Product must have a description");
        }else if(!productHasExpDate(product)){
            throw new ProductException("product must have an expiring date");
        }else if(!productHasQuantity(product)){
            throw new ProductException("product must have a quantity");
        }else if(!productHasPrice(product)){
            throw new ProductException("product must have a price");
        }
        return save(product);

    }

    private boolean productHasName(Product product){
       return product.getName() != null;
    }
    private boolean productHasPrice(Product product){
        return product.getPrice() != null;
    }

    private boolean productHasDescription(Product product){
        return product.getDescription() != null;
    }
    private  boolean productHasExpDate(Product product){
        return product.getExpDate() != null;
    }
    private boolean productHasQuantity(Product product){
        return product.getQuantity() != null;
    }




}
