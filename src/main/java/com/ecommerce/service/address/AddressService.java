package com.ecommerce.service.address;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;

import java.util.List;

public interface AddressService  {
    public Address saveAddress(Address address);
     public Address updateAddress (Address address)throws  AddressException;
    public Address findAddressById(Integer id);
    public List<Address> findAllAddress();
    public void deleteById(Integer id);


}
