package br.com.fiap.postech.customers.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

/**
 * Entity class representing a customer.
 */
@Entity
@Table(name = "TB_CUSTOMERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    /**
     * Name of the customer.
     */
    @Column
    private String name;

    /**
     * Email address of the customer.
     */
    @Email
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    /**
     * Phone number of the customer.
     */
    @Column
    private String phone;

    /**
     * Cell phone number of the customer.
     */
    @Column
    private String cellPhone;

    /**
     * Zip code of the customer's address.
     */
    @Column
    private String zipCode;

    /**
     * Address of the customer.
     */
    @Column
    private String address;

    /**
     * Address number of the customer.
     */
    @Column
    private String addressNumber;

    /**
     * Neighborhood of the customer's address.
     */
    @Column
    private String neighborhood;

    /**
     * City of the customer's address.
     */
    @Column
    private String city;

    /**
     * State of the customer's address.
     */
    @Column
    private String state;

    /**
     * Additional address information.
     */
    @Column
    private String complement;
}