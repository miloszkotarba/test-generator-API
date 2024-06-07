package com.mkotarba.first_crud_api.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test")
public class Test {
    @Id
    private String testId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Duration is required")
    private int duration;

    @DocumentReference(collection = "exercise")
    private List<Exercise> exercises = new ArrayList<>();
}
