package com.shobujghor.app.inventory.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository extends AbstractDynamoDbRepository<Item> {
    public ItemRepository(DynamoDBMapper mapper) {
        super(mapper, Item.class);
    }

    public List<Item> getItemListByCategory(String categoryName) {
        var item = Item.builder()
                .categoryName(categoryName)
                .build();

        var queryExpression = new DynamoDBQueryExpression<Item>()
                .withHashKeyValues(item).withConsistentRead(false);

        return queryPage(queryExpression);
    }
}
