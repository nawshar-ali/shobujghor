package com.shobujghor.app.cart.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository extends AbstractDynamoDbRepository<Cart> {
    public CartRepository(DynamoDBMapper mapper) {
        super(mapper, Cart.class);
    }
}
