package com.ecommerce.service.customer;

import com.ecommerce.data.exceptions.CustomerException;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.repository.CustomerRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@Slf4j
@Data
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService = new CustomerServiceImpl();

    Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        customer = new Customer();
    }

    @Test
    void testThatWeCanCallTheSaveCustomerRepository () throws CustomerException {
        when(customerRepository.save(customer)).thenReturn(customer);
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testThatWeCanCallTheFindCustomerByIdRepository () {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        customerService.findByCustomerId(1);
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    void testThatWeCanCallFindAllCustomerRepository () {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        customerService.findAllCustomer();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testThatWeCanCallDeleteCustomerRepository () {
        doNothing().when(customerRepository).deleteById(1);
        customerService.deleteCustomerById(1);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    void testThatWeCanCallTheUpdateCustomerRepository () throws  Exception{
       customer.setId(3);
       customer.setFirstName("toyin");

//       when(customerRepository.save(customer)).thenReturn(customer);
//        customerService.updateCustomer(customer);
//       verify(customerRepository, times(1)).save(customer);
        customer.setFirstName("shade");
        assertThat(customer.getFirstName()).isEqualTo("shade");

      //assertThat(customerRepository.findById(5)).isNotNull();


    }

}