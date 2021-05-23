package com.ecommerce.web.controller.address;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Card;
import com.ecommerce.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("/create")
    public ResponseEntity<?> saveAddress(@RequestBody Address address)  {
        addressService.saveAddress(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public  ResponseEntity<?> updateAddress(@RequestBody Address address) throws AddressException {
        addressService.updateAddress(address);
        return  new ResponseEntity<>(address,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable Integer id){
        Address address = addressService.findAddressById(2);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Integer id){
        addressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllAddress(){
        List<Address> addresses = addressService.findAllAddress();
        return new ResponseEntity<>(addresses,HttpStatus.OK);

    }

}
