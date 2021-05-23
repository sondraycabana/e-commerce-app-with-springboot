package com.ecommerce.web.controller.product;

import com.ecommerce.data.model.Product;
import com.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity(products,HttpStatus.OK);

    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Product product) throws  Exception{
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleting/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        productService.deleteProductById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return  new ResponseEntity<>(HttpStatus.CREATED);
}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product,  HttpStatus.OK);
    }
}
