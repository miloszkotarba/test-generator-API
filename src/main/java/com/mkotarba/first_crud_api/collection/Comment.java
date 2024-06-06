package com.mkotarba.first_crud_api.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String commentId;

    @NotNull(message = "Content is required")
    private String content;

    @NotBlank(message = "Nickname is required")
    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd, HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;
}
