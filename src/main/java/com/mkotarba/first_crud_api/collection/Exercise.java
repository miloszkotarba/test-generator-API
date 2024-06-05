package com.mkotarba.first_crud_api.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Builder
@Document(collection = "exercise") // This annotation is used to specify the collection name in MongoDB
public class Exercise {
    @Id
    private String exerciseId;
    private String content;
    private String solution;
    private String difficultyLevel;

    @DocumentReference(collection = "category")
    private Category category;
}
