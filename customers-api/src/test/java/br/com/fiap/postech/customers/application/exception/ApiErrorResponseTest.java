package br.com.fiap.postech.customers.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseTest {

    @Test
    void shouldReturnType() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "detail");
        assertEquals("type", response.getType());
    }

    @Test
    void shouldReturnTitle() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "detail");
        assertEquals("title", response.getTitle());
    }

    @Test
    void shouldReturnStatus() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "detail");
        assertEquals(400, response.getStatus());
    }

    @Test
    void shouldReturnDetail() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "detail");
        assertEquals("detail", response.getDetail());
    }

    @Test
    void shouldHandleNullDetail() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, null);
        assertNull(response.getDetail());
    }

    @Test
    void shouldHandleEmptyDetail() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "");
        assertEquals("", response.getDetail());
    }
}