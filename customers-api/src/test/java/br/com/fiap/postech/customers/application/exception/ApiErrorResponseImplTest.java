package br.com.fiap.postech.customers.application.exception;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseImplTest {

    @Test
    void shouldReturnCurrentTimestamp() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", 400, "detail");
        assertNotNull(response.getTimestamp());
        assertTrue(response.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void shouldHandleNullType() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl(null, "title", 400, "detail");
        assertNull(response.getType());
    }

    @Test
    void shouldHandleEmptyType() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("", "title", 400, "detail");
        assertEquals("", response.getType());
    }

    @Test
    void shouldHandleNullTitle() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", null, 400, "detail");
        assertNull(response.getTitle());
    }

    @Test
    void shouldHandleEmptyTitle() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "", 400, "detail");
        assertEquals("", response.getTitle());
    }

    @Test
    void shouldHandleNegativeStatus() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("type", "title", -1, "detail");
        assertEquals(-1, response.getStatus());
    }
}