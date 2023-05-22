package com.innovation.acceptOrDecline.repository;

import com.innovation.common.config.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.innovation.acceptOrDecline.entity.Innovation;;
import org.springframework.stereotype.Repository;

@Repository
public class InnovationRepository {


    private DynamoDBMapper dynamoDBMapper;
    public void save(Innovation innovation){
        dynamoDBMapper.save(innovation);
    };
}

