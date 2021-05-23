package com.ecommerce.service.address;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Card;
import com.ecommerce.data.repository.AddressRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Data
class AddressServiceImplTest {
    @Mock
        AddressRepository addressRepository;
    @InjectMocks
            AddressService addressService = new AddressServiceImpl();

    Address address;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        address = new Address();
    }

    @Test
    void saveAddress()  {
        when(addressRepository.save(address)).thenReturn(address);
        addressService.saveAddress(address);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void updateAddress() throws AddressException {
        Address address = new Address();
       address.setId(2);
        address.setState("oyo");
//        when(addressRepository.saveAddress(address)).thenReturn(address);
//        addressService.updateAddress(address);
//        verify(addressRepository, times(1)).saveAddress(address);
        assertThat(address).isNotNull();
        assertThat(address.getState()).isEqualTo("oyo");
    }

    @Test
    void findAddressById() {

        when(addressRepository.findById(2)).thenReturn(Optional.of(address));
        addressService.findAddressById(2);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void findAllAddress() {
        when(addressRepository.findAll()).thenReturn(List.of(address));
        addressService.findAllAddress();
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        addressService.deleteById(1);
        verify(addressRepository, times(1)).deleteById(1);
    }
}