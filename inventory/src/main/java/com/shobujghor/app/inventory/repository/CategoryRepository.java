package com.shobujghor.app.inventory.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractDynamoDbRepository<Category> {
    public CategoryRepository(DynamoDBMapper mapper) {
        super(mapper, Category.class);
    }
}
