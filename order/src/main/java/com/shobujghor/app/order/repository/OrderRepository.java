package com.shobujghor.app.order.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends AbstractDynamoDbRepository<Order> {
    public OrderRepository(DynamoDBMapper mapper) {
        super(mapper, Order.class);
    }
}
