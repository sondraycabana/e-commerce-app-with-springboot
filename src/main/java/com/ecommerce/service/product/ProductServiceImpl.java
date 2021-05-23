package com.ecommerce.service.product;

import com.ecommerce.data.exceptions.ProductException;
import com.ecommerce.data.model.Product;
import com.ecommerce.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) throws ProductException {
        if(product.getId() == null){
            throw new ProductException("the product is not in our database ");
        }
        Product product1 =  productRepository.findById(product.getId()).get();

        if(product.getName() != null){
            product1.setName(product.getName());
        }
        if(product.getDescription() != null){
            product1.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            product1.setPrice(product.getPrice());
        }
        if(product.getQuantity() != null){
            product1.setPrice(product.getPrice());
        }
        if(product.getExpDate() != null){
            product1.setExpDate(product.getExpDate());
        }
        return productRepository.saveProduct(product1);
    }
}
