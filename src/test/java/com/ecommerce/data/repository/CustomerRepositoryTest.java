package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.CustomerException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Sql(scripts = "classpath:db/insert.sql")
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    Customer customer;
    @BeforeEach
    void setUp() {
        customer = new Customer();
    }
    @Test
    void testThatWeCanSaveCustomer () {
        customer.setContact("09031861100");
        customer.setEmail("bj@gmail.com");
        customer.setFirstName("tony");
        customer.setLastName("alonge");
        customer.setPassword("iclass123");

        Address address = addressRepository.findById(1).orElse(null);
        customer.setAddresses(address);
        log.info("when  it saves ->{}",customer);
        assertDoesNotThrow(() -> customerRepository.save(customer));
    }

    @Test
    void testThatTwoCustomersCanShareOneAddress () {
       customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(1).orElse(null);
        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAddresses () {
        customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(1).orElse(null);

        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress () {
        customer = customerRepository.findById(1).orElse(null);

        assert customer != null;
        for (Address address : customer.getAddresses()) {
           log.info("address -> {}", address.getStreet());
       }

       assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanRemoveAnAddressFromACustomerAddressesList () {
        customer =  customerRepository.findById(1).orElse(null);

        assert customer != null;

        Address address = addressRepository.findById(1).orElse(null);

        if (customer.getAddresses().contains(address)) {
            customer.getAddresses().remove(address);
        }

        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }

    @Test
    void testThatWeCanUpdateCustomerDetails () {
        Customer newCustomer = new Customer();
        newCustomer.setId(1);
        newCustomer.setFirstName("lagbaja");

        customer = customerRepository.findById(newCustomer.getId()).orElse(null);

        if (newCustomer.getFirstName() != null) {
            customer.setFirstName(newCustomer.getFirstName());
        }

        if (newCustomer.getLastName() != null) {
            customer.setLastName(newCustomer.getLastName());
        }
        if (newCustomer.getEmail() != null){
            customer.setEmail(newCustomer.getEmail());
        }
        if(newCustomer.getContact() != null){
            customer.setContact(newCustomer.getContact());
        }
        if(newCustomer.getPassword() != null){
            customer.setPassword(newCustomer.getPassword());
        }
        customerRepository.save(newCustomer);
        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getFirstName()).isEqualTo("lagbaja");
    }


    @Test
    void testThatWeCanDeleteAddress () {
        assertThat(customerRepository.existsById(2)).isTrue();
        customerRepository.deleteById(2);
        assertThat(customerRepository.existsById(2)).isFalse();
    }

}









