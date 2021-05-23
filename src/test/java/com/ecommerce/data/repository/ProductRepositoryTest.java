package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.ProductException;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    Product product;
    Order order;
    Customer customer;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testThatWeCanSaveAProduct () throws ProductException {
        product.setName("Potatoe");
        product.setPrice(500.00);
        product.setDescription(" sweet potatoes");
        product.setExpDate("12-10-12");
        product.setQuantity(5);



        productRepository.saveProduct(product);
        assertThat(product.getId()).isNotNull();
    }

@Test
    void testThatWeCanUpdateAProduct() throws ProductException {
   // customer = customerRepository.findById(2).orElse(null);
//    customer.setFirstName("Benson");

//    assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
//    assertThat(customer.getFirstName()).isEqualTo("Benson");

        product = productRepository.findById(3).orElse(null);
        product.setPrice(1500.00);
       productRepository.saveProduct(product);
        log.info("when we update product ->{}", product);
    }

    @Test
    void testThatWeCanDeleteAProduct(){
       productRepository.findById(2).orElse(null);
       productRepository.deleteById(2);
       assertThat(productRepository.existsById(2)).isFalse();

    }
    @Test
    void testThatWeCanFindById(){
        Product product = productRepository.findById(3).orElse(null);
        assertThat(productRepository.existsById(3)).isTrue();
        log.info("find product by id ->{}",product);
    }
    @Test
    void testThatWeCanGetAllProduct(){
        List <Product> product = productRepository.findAll();
        log.info("get all product ->{}",product);
    }

}