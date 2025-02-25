package br.com.fiap.postech.customers.infrastructure.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Filter class for searching customers based on various criteria.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerFilter {

    /**
     * Unique identifier for the customer.
     */
    private Long id;

    /**
     * Name of the customer.
     */
    private String name;

    /**
     * Email address of the customer.
     */
    private String email;

    /**
     * Phone number of the customer.
     */
    private String phone;

    /**
     * Cell phone number of the customer.
     */
    private String cellPhone;

    /**
     * Zip code of the customer's address.
     */
    private String zipCode;

    /**
     * Neighborhood of the customer's address.
     */
    private String neighborhood;

    /**
     * City of the customer's address.
     */
    private String city;

    /**
     * State of the customer's address.
     */
    private String state;

    /**
     * Constructor for creating a CustomerFilter with only an ID.
     *
     * @param customerID the unique identifier for the customer
     */
    public CustomerFilter(final Long customerID) {
        this.id = customerID;
    }
}