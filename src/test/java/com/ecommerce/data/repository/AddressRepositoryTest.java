package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
//@Sql(scripts = "classpath:db/insert.sql")
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired CustomerRepository customerRepository;

    Address address;
    Customer customer;

    @BeforeEach
    void setUp() {

        address = new Address();
        customer = new Customer();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanSaveAnAddress () {
        address.setState("Kano");
        address.setCity("Zaza");
        address.setCountry("Nigeria");
        address.setStreet("12 aminu way, Sabo");
        address.setZipcode("1102233");

//        Customer customer = customerRepository.findById(1).get();
        Customer customer = customerRepository.findById(1).orElse(null);
        address.setCustomers(customer);

        assertDoesNotThrow(() ->  addressRepository.saveAddress(address));
    }

    @Test
    void testThatWeCanUpdateAddress () throws AddressException {
        address = addressRepository.findById(2).orElse(null);
        address.setZipcode("200000");
        address.setState("Zamfara");
        addressRepository.saveAddress(address);

        assertThat(address.getZipcode()).isEqualTo("200000");
        log.info("Address -> {}", address);
    }

    @Test
    void testThatWeCanDeleteAddress () {
        assertThat(addressRepository.existsById(3)).isTrue();
        addressRepository.deleteById(3);
        assertThat(addressRepository.existsById(3)).isFalse();
    }

    @Test
    void testThatWeGetAllAddresses () {
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isNotEmpty();
    }

}