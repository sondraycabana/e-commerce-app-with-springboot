package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public default Order saveOrder(Order order) throws OrderException {
        if (!orderHasCustomer(order)) {
            throw new OrderException("Customer can not be null");
        } else if (!orderHasDate(order)) {
            throw new OrderException("Order must have a date");
        }else if(!orderHasDelivery()){
            throw new OrderException("Order must have delivery");
        }else if(!orderHasCancelOptional()){
            throw new OrderException("Order can be canceled");
        }else if(!orderHasProduct(order)){
            throw new OrderException("Order must have a product");
        }
        return save(order);

    }

    private boolean orderHasCustomer (Order order) {
        return order.getCustomer() != null;

    }

    private boolean orderHasDate (Order order) {
        return order.getDate() != null;
    }

    private boolean orderHasDelivery(){
        return true;
    }
    private boolean orderHasCancelOptional(){
        return true;
    }
    private boolean orderHasProduct(Order order){
        return order.getProducts() != null;
    }
}
