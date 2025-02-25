package br.com.fiap.postech.customers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CustomersApplication.class)
@ActiveProfiles("test")
class CustomersApplicationTest {

    @Test
    void main_runsSpringApplication() {
        String[] args = {"--server.port=9999"};
        assertDoesNotThrow(() -> CustomersApplication.main(args));
    }
}