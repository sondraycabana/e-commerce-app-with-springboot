package com.ecommerce.service.address;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.exceptions.CustomerException;
import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;
    Customer customer = new Customer();
    @Override
    public Address saveAddress(Address address)  {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) throws AddressException {

        if(address.getId() == null){
            throw new AddressException("Id can not be null");
        }

        Address address1 = addressRepository.findById(address.getId()).orElse(null);


        if(address1 == null){
            throw new AddressException("this  address is not in our database");
        }else{
                    if(address.getStreet() != null){
            address1.setStreet(address.getStreet());
        }
            if(address.getCity() != null){
                address1.setCity(address.getCity());
            } if(address.getZipcode() != null){
                address1.setZipcode(address.getZipcode());
            } if(address.getCountry() != null){
                address1.setState(address.getState());
            } if (address.getCustomers() != null){
                address1.setCustomers(customer);
            }
            return addressRepository.saveAddress(address1);
        }

    }

    @Override
    public Address findAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }
}
