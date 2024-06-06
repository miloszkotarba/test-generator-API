package com.mkotarba.first_crud_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing // This annotation is used to enable auditing in MongoDB (e.g. @CreatedDate, @LastModifiedDate)
public class MongoDBConfig {
}
