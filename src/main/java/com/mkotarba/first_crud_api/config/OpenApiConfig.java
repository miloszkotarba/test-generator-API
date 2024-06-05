package com.mkotarba.first_crud_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Test Generator API",
                version = "1.0",
                description = "Test Generator API for generating tests for students. " +
                        "Authors: Miłosz Kotarba, Jolanta Smarzych, Piotr Komorowski."
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        url = "http://localhost:8080",
                        description = "Dev server"
                )
        }
)

public class OpenApiConfig {
}
