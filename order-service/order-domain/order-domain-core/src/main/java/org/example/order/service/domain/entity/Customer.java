package org.example.order.service.domain.entity;

import org.example.domain.entity.AggregateRoot;
import org.example.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    public Customer() {
    }
}
