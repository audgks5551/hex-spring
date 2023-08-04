package org.example.order.service.dataaccess.consumer.mapper;

import org.example.domain.valueobject.CustomerId;
import org.example.order.service.dataaccess.consumer.entity.CustomerEntity;
import org.example.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
