package br.com.fiap.postech.customers.interfaces.rest;

import br.com.fiap.postech.customers.application.usecases.customer.CreateCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.DeleteCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.FindCustomerUseCase;
import br.com.fiap.postech.customers.application.usecases.customer.UpdateCustomerUseCase;
import br.com.fiap.postech.customers.domain.model.Customer;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomerFilter;
import br.com.fiap.postech.customers.interfaces.adapters.CustomerRestAdapter;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerRequestDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerResponseDTO;
import br.com.fiap.postech.customers.interfaces.dtos.CustomerUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerRestAdapter customerRestAdapter;
    private final FindCustomerUseCase findCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    /**
     * Constructor for CustomersController.
     *
     * @param customerRestAdapter   Adapter for converting between domain and DTO objects
     * @param findCustomerUseCase   Use case for finding customers
     * @param createCustomerUseCase Use case for creating customers
     * @param updateCustomerUseCase Use case for updating customers
     * @param deleteCustomerUseCase Use case for deleting customers
     */
    public CustomersController(
            CustomerRestAdapter customerRestAdapter,
            FindCustomerUseCase findCustomerUseCase,
            CreateCustomerUseCase createCustomerUseCase,
            UpdateCustomerUseCase updateCustomerUseCase,
            DeleteCustomerUseCase deleteCustomerUseCase
    ) {
        this.customerRestAdapter = customerRestAdapter;
        this.findCustomerUseCase = findCustomerUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    /**
     * Filters customers based on the provided customerId.
     *
     * @return a ResponseEntity containing a list of CustomerResponseDTO objects
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable final Long customerId) {
        Customer customers = findCustomerUseCase.findById(customerId);
        CustomerResponseDTO responseList = customerRestAdapter.toResponse(customers);
        return ResponseEntity.ok(responseList);
    }

    /**
     * Filters customers based on the provided filter criteria.
     *
     * @param customerFilter the filter criteria
     * @return a ResponseEntity containing a list of CustomerResponseDTO objects
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> filter(final CustomerFilter customerFilter) {
        List<Customer> customers = findCustomerUseCase.filter(customerFilter);
        List<CustomerResponseDTO> responseList = customers.stream().map(customerRestAdapter::toResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    /**
     * Creates a new customer.
     *
     * @param requestDTO the customer request data transfer object
     * @return a ResponseEntity containing the created CustomerResponseDTO object
     */
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(@RequestBody @Valid CustomerRequestDTO requestDTO) {
        Customer customer = customerRestAdapter.toDomain(requestDTO);
        Customer savedCustomer = createCustomerUseCase.execute(customer);
        CustomerResponseDTO responseDTO = customerRestAdapter.toResponse(savedCustomer);
        return ResponseEntity.created(URI.create("/customers/" + responseDTO.id())).body(responseDTO);
    }

    /**
     * Updates an existing customer by their ID.
     *
     * @param id         the ID of the customer to update
     * @param requestDTO the customer update request data transfer object
     * @return a ResponseEntity containing the updated CustomerResponseDTO object
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomerById(@PathVariable final Long id, @RequestBody CustomerUpdateRequestDTO requestDTO) {
        Customer customer = customerRestAdapter.toDomain(requestDTO);
        Customer updatedCustomer = updateCustomerUseCase.updateCustomerById(id, customer);
        CustomerResponseDTO responseDTO = customerRestAdapter.toResponse(updatedCustomer);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable final Long id) {
        deleteCustomerUseCase.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a customer by their email.
     *
     * @param email the email of the customer to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("email/{email}")
    public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable final String email) {
        deleteCustomerUseCase.deleteCustomerByEmail(email);
        return ResponseEntity.noContent().build();
    }
}