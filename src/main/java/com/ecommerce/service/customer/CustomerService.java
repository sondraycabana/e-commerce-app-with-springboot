package com.ecommerce.service.customer;

import com.ecommerce.data.exceptions.CustomerException;
import com.ecommerce.data.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer (Customer customer) throws CustomerException;
    public Customer findByCustomerId (Integer id);
    public List<Customer> findAllCustomer ();
    public void deleteCustomerById (Integer id);
    public Customer updateCustomer(Customer customer) throws Exception;

}
