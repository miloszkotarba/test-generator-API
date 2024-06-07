package com.mkotarba.first_crud_api.collection;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "exercise") // This annotation is used to specify the collection name in MongoDB
public class Exercise {
    @Id
    private String exerciseId;

    @NotBlank(message = "Content is required")
    private String content;

    @NotBlank(message = "Solution is required")
    private String solution;

    @NotBlank(message = "Difficulty level is required")
    private String difficultyLevel;

    @DocumentReference(collection = "category")
    private Category category;

    @DocumentReference(collection = "comment")
    private List<Comment> comments = new ArrayList<>();

}
