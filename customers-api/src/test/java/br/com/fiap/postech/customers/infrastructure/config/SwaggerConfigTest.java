package br.com.fiap.postech.customers.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {
    @Test
    void publicApiBeanIsNotNull() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        assertNotNull(swaggerConfig.publicApi());
    }

    @Test
    void publicApiBeanReturnsCorrectType() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        assertTrue(swaggerConfig.publicApi() instanceof GroupedOpenApi);
    }

    @Test
    void customOpenAPIBeanIsNotNull() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        assertNotNull(swaggerConfig.customOpenAPI());
    }

    @Test
    void customOpenAPIBeanReturnsCorrectType() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        assertTrue(swaggerConfig.customOpenAPI() instanceof OpenAPI);
    }

    @Test
    void customOpenAPIBeanHasCorrectInfo() {
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertEquals("Customers API", openAPI.getInfo().getTitle());
        assertEquals("v3", openAPI.getInfo().getVersion());
        assertEquals("Customers API - v1", openAPI.getInfo().getDescription());
    }

}