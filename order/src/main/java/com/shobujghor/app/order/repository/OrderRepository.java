package com.shobujghor.app.order.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository extends AbstractDynamoDbRepository<Order> {
    public OrderRepository(DynamoDBMapper mapper) {
        super(mapper, Order.class);
    }

    public List<Order> getOrderList(String customerEmail) {

        var order = Order.builder()
                .customerEmail(customerEmail)
                .build();

        var queryExpression = new DynamoDBQueryExpression<Order>()
                .withHashKeyValues(order);

        return getData(queryExpression);

    }
}
