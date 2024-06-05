package com.mkotarba.first_crud_api.collection;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(hidden = true)// hide this class from the Swagger documentation
// DATA TRANSFER OBJECT
public class CategoryDto {
    private String name;
    private String description;
}
