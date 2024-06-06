package com.mkotarba.first_crud_api.collection;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
/*
@JsonInclude(JsonInclude.Include.NON_NULL) // This annotation is used to exclude null fields from the JSON output
*/
public class Category {
    @Id
    private String categoryId;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}

