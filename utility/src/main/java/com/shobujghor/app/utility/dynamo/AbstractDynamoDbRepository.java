package com.shobujghor.app.utility.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractDynamoDbRepository<T> {


    private final DynamoDBMapper mapper;
    private final Class<T> tClass;


    public Optional<T> getData(Object key) {
        try {

            return Optional.ofNullable(mapper.load(tClass, key));
        } catch (Exception ex) {
            log.error("Data retrieval failed from DynamoDB using key: {}", key, ex);
        }

        return Optional.empty();
    }

    public Optional<T> getData(Object hashKey, Object rangeKey) {
        try {
            return Optional.ofNullable(mapper.load(tClass, hashKey, rangeKey));
        } catch (Exception ex) {
            log.error("Data retrieval failed from DynamoDB using hashKey : {} , rangeKey : {}", hashKey, rangeKey, ex);
        }

        return Optional.empty();
    }

    public List<T> getData(DynamoDBQueryExpression<T> queryExpression) {
        try {
            return mapper.query(tClass, queryExpression);
        } catch (Exception ex) {
            log.error("Failed to fetch data using expression: {}", queryExpression, ex);
        }

        return new ArrayList<>();
    }

    public List<Object> batchLoadDataByHashKey(String tableName, List<Object> itemsToGet) {
        try {
            return mapper.batchLoad(itemsToGet).get(tableName);
        } catch (Exception ex) {
            log.error("Failed to batch load data by hash keys: {}", itemsToGet.toString(), ex);
        }
        return new ArrayList<>();
    }

    public List<T> queryPage(DynamoDBQueryExpression<T> queryExpression) {
        try {
            return mapper.queryPage(tClass, queryExpression).getResults();
        } catch (Exception ex) {
            log.error("Failed to query page using expression: {}", queryExpression.toString(), ex);
        }

        return new ArrayList<>();
    }


    protected List<T> queryData(DynamoDBQueryExpression<T> queryExpression) {
        try {
            return mapper.query(tClass, queryExpression);
        } catch (Exception ex) {
            log.error("Failed to query data using expression: {}", queryExpression.toString(), ex);
        }

        return new ArrayList<>();
    }

    public boolean saveData(T model) {
        try {
            mapper.save(model);

            return true;
        } catch (Exception ex) {
            log.error("Failed to save data: {}", model, ex);
            throw new RuntimeException("Data save failed", ex);
        }
    }

    public List<DynamoDBMapper.FailedBatch> saveMultipleData(List<T> models) {
        try {
            return mapper.batchSave(models);
        } catch (Exception ex) {
            log.error("Failed to save multiple data: {}", models, ex);
            throw new RuntimeException("Failed to save multiple data", ex);
        }
    }

    public boolean deleteData(T model) {
        try {
            mapper.delete(model);

            return true;
        } catch (Exception ex) {
            log.error("Failed to delete {} from DynamoDB", model, ex);
            throw new RuntimeException("Failed to delete from DynamoDB", ex);
        }
    }

    public List<T> scanData(Class<T> clazz, DynamoDBScanExpression expression) {

        return mapper.scan(clazz, expression);
    }

    public ScanResultPage<T> scanPage(Class<T> clazz, DynamoDBScanExpression expression) {
        return mapper.scanPage(clazz, expression);
    }

    public List<T> getListFromPaginatedScan(Class<T> clazz) {

        var scanExpression = new DynamoDBScanExpression();
        var tList = new ArrayList<T>();
        var isListConstructionComplete = false;


        while (!isListConstructionComplete) {
            var errorCodeDaoScanResultPage = scanPage(clazz, scanExpression);
            tList.addAll(errorCodeDaoScanResultPage.getResults());
            var lastEvaluatedKey = errorCodeDaoScanResultPage.getLastEvaluatedKey();
            if (lastEvaluatedKey != null) {
                scanExpression = new DynamoDBScanExpression();
                scanExpression.setExclusiveStartKey(lastEvaluatedKey);
            } else {
                isListConstructionComplete = true;
            }
        }

        return tList;

    }

    protected PaginatedQueryList<T> getPaginatedObjectList(DynamoDBQueryExpression<T> queryExpression) {
        return mapper.query(tClass, queryExpression);
    }

}
