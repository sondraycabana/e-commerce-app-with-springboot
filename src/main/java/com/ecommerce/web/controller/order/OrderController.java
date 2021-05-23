package com.ecommerce.web.controller.order;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) throws OrderException {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public  ResponseEntity<?> updateOrder(@RequestBody Order order) throws OrderException {
        orderService.updateOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Integer id){
       Order order = orderService.findOrderById(1);
       return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllOrder(){
        List<Order> orderList = orderService.findAllOrders();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }
}
