package br.com.fiap.postech.customers.infrastructure.repository.filter;

import br.com.fiap.postech.customers.infrastructure.entity.CustomerEntity;

import java.util.List;

/**
 * Interface for custom repository methods for CustomerEntity.
 */
public interface CustomersRepositoryQuery {

    /**
     * Filters customers based on the given CustomerFilter criteria.
     *
     * @param customerFilter the filter criteria
     * @return a list of CustomerEntity that match the filter criteria
     */
    List<CustomerEntity> filter(CustomerFilter customerFilter);
}