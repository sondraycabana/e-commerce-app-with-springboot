package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    default Address saveAddress (Address address) throws AddressException {
        if (!addressHasCustomer(address)) {
            throw new AddressException("Address must have a customer");
        } else if (!addressHasCountry(address)) {
            throw new AddressException("Country must be provided");
        } else if (!addressHasState(address)) {
            throw new AddressException("No state provided");
        } else if (!addressHasStreet(address)) {
            throw new AddressException("Street not provided");
        }
        return save(address);
    }

    private boolean addressHasCustomer (Address address) {
        return address.getCustomers() != null;
    }

    private boolean addressHasCountry (Address address) {
        return address.getCountry() != null ;
    }
    private boolean addressHasState (Address address) {
        return address.getState() != null;
    }

    private boolean addressHasStreet (Address address) {
        return address.getStreet() != null;
    }
}
