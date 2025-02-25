package br.com.fiap.postech.customers.infrastructure.repository;

import br.com.fiap.postech.customers.infrastructure.entity.CustomerEntity;
import br.com.fiap.postech.customers.infrastructure.repository.filter.CustomersRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for CustomerEntity.
 * Extends JpaRepository to provide CRUD operations and CustomersRepositoryQuery for custom queries.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomersRepositoryQuery {

    /**
     * Finds a CustomerEntity by email.
     *
     * @param email the email address to search for
     * @return an Optional containing the found CustomerEntity, or empty if not found
     */
    Optional<CustomerEntity> findCustomerEntityByEmail(final String email);

}