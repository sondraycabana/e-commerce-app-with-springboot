package com.ecommerce.service.customer;

import com.ecommerce.data.exceptions.CustomerException;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer = new Customer();

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerException {

        return customerRepository.save (customer);
    }

    @Override
    public Customer findByCustomerId(Integer id) {

        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException
    {
        if(customer.getId() == null){
            throw  new CustomerException(" Id cannot be null");
        }

        Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
        //Customer customer1 = customerRepository.findById(customer.getId()).get();


        if(customer1 == null){
            throw new CustomerException("customer does not exist");
        }

        if(customer.getPassword() != null){
            customer1.setPassword((customer.getPassword()));
        }
        if(customer.getFirstName() != null){
            customer1.setFirstName(customer.getFirstName());
        }
        if(customer.getLastName() != null){
            customer1.setLastName(customer.getLastName());
        }
        if(customer.getContact() != null){
            customer1.setContact(customer.getContact());
        }
        if(customer.getEmail() != null){
            customer1.setEmail(customer.getEmail());
        }
        return customerRepository.saveCustomer(customer1);
    }

}
