package br.com.fiap.postech.customers.infrastructure.repository.filter;

import br.com.fiap.postech.customers.infrastructure.entity.CustomerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRepositoryImplTest {

    private CustomerRepositoryImpl customerRepository;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<CustomerEntity> criteriaQuery;
    private TypedQuery<CustomerEntity> typedQuery;

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepositoryImpl();
        entityManager = mock(EntityManager.class);
        criteriaBuilder = mock(CriteriaBuilder.class);
        criteriaQuery = mock(CriteriaQuery.class);
        Root root = mock(Root.class);
        typedQuery = mock(TypedQuery.class);

        customerRepository.entityManager = entityManager;

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(CustomerEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(CustomerEntity.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
    }

    @Test
    void filterReturnsMatchingCustomers() {
        CustomerFilter filter = new CustomerFilter(1L, "John Doe", "john.doe@example.com", "123456789", "987654321", "12345", "Downtown", "Metropolis", "NY");
        List<CustomerEntity> expectedCustomers = List.of(new CustomerEntity());
        when(typedQuery.getResultList()).thenReturn(expectedCustomers);

        List<CustomerEntity> result = customerRepository.filter(filter);

        assertEquals(expectedCustomers, result);
    }

    @Test
    void filterHandlesNullFilter() {
        CustomerFilter filter = new CustomerFilter();
        List<CustomerEntity> expectedCustomers = List.of(new CustomerEntity());
        when(typedQuery.getResultList()).thenReturn(expectedCustomers);

        List<CustomerEntity> result = customerRepository.filter(filter);

        assertEquals(expectedCustomers, result);
    }

    @Test
    void filterHandlesEmptyStrings() {
        CustomerFilter filter = new CustomerFilter(null, "", "", "", "", "", "", "", "");
        List<CustomerEntity> expectedCustomers = List.of(new CustomerEntity());
        when(typedQuery.getResultList()).thenReturn(expectedCustomers);

        List<CustomerEntity> result = customerRepository.filter(filter);

        assertEquals(expectedCustomers, result);
    }

    @Test
    void filterHandlesNullValues() {
        CustomerFilter filter = new CustomerFilter(null, null, null, null, null, null, null, null, null);
        List<CustomerEntity> expectedCustomers = List.of(new CustomerEntity());
        when(typedQuery.getResultList()).thenReturn(expectedCustomers);

        List<CustomerEntity> result = customerRepository.filter(filter);

        assertEquals(expectedCustomers, result);
    }

    @Test
    void filterHandlesPartialFilter() {
        CustomerFilter filter = new CustomerFilter(null, "John Doe", null, null, null, null, null, null, null);
        List<CustomerEntity> expectedCustomers = List.of(new CustomerEntity());
        when(typedQuery.getResultList()).thenReturn(expectedCustomers);

        List<CustomerEntity> result = customerRepository.filter(filter);

        assertEquals(expectedCustomers, result);
    }
}