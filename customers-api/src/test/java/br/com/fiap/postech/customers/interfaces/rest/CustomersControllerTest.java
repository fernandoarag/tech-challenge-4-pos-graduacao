package br.com.fiap.postech.customers.interfaces.rest;

import br.com.fiap.postech.customers.application.usecases.customer.CreateCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.DeleteCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.FindCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.UpdateCustomerUseCase;
import br.com.fiap.postech.customers.domain.model.Address;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import br.com.fiap.postech.customers.interfaces.adapters.CustomerRestAdapter;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerRequestDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerResponseDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerUpdateRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomersControllerTest {

    @Mock
    private CustomerRestAdapter customerRestAdapter;

    @Mock
    private FindCustomerUseCase findCustomerUseCase;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @Mock
    private UpdateCustomerUseCase updateCustomerUseCase;

    @Mock
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @InjectMocks
    private CustomersController customersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void filterReturnsListOfCustomers() {
        CustomerFilter filter = new CustomerFilter();
        final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        List<Customer> customers = List.of(new Customer(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", address));
        when(findCustomerUseCase.filter(filter)).thenReturn(customers);
        List<CustomerResponseDTO> responseList = customers.stream().map(customerRestAdapter::toResponse).toList();

        ResponseEntity<List<CustomerResponseDTO>> response = customersController.filter(filter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseList, response.getBody());
    }

    @Test
    void createCustomerReturnsCreatedCustomer() {
        final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        Customer customer = new Customer(null, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        Customer savedCustomer = new Customer(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");

        when(customerRestAdapter.toDomain(requestDTO)).thenReturn(customer);
        when(createCustomerUseCase.execute(customer)).thenReturn(savedCustomer);
        when(customerRestAdapter.toResponse(savedCustomer)).thenReturn(responseDTO);

        ResponseEntity<CustomerResponseDTO> response = customersController.create(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        assertEquals(URI.create("/customers/1"), response.getHeaders().getLocation());
    }

    @Test
    void updateCustomerWithoutEmailCustomerByIdReturnsUpdatedCustomer() {
        Long id = 1L;
        final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        CustomerUpdateRequestDTO requestDTO = new CustomerUpdateRequestDTO("John Doe", "123456789", "987654321", "12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");
        Customer customer = new Customer(id, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        Customer updatedCustomer = new Customer(id, "John Doe", "john.doe@example.com", "123456789", "987654321", address);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");

        Mockito.when(customerRestAdapter.toDomain(requestDTO)).thenReturn(customer);
        Mockito.when(updateCustomerUseCase.updateCustomerById(id, customer)).thenReturn(updatedCustomer);
        Mockito.when(customerRestAdapter.toResponse(updatedCustomer)).thenReturn(responseDTO);

        ResponseEntity<CustomerResponseDTO> response = customersController.updateCustomerById(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void deleteCustomerByIdReturnsNoContent() {
        Long id = 1L;

        ResponseEntity<Void> response = customersController.deleteCustomerById(id);

        Mockito.verify(deleteCustomerUseCase).deleteCustomerById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteCustomerByEmailReturnsNoContent() {
        String email = "john.doe@example.com";

        ResponseEntity<Void> response = customersController.deleteCustomerByEmail(email);

        Mockito.verify(deleteCustomerUseCase).deleteCustomerByEmail(email);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}