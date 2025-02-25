package br.com.fiap.postech.logistics.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "delivery_addresses")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddressEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String name;

    private String addressNumber;
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String complement;

    @Version
    private Long version;

    public DeliveryAddressEntity(UUID id, String zipCode, String name, String addressNumber, String neighborhood,
                                 String city, String state, String complement) {
        this.id = id;
        this.zipCode = zipCode;
        this.name = name;
        this.addressNumber = addressNumber;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    public static DeliveryAddressEntity create(UUID id, String zipCode, String name, String addressNumber,
                                               String neighborhood, String city, String state, String complement){
        return new DeliveryAddressEntity(id, zipCode, name, addressNumber, neighborhood, city, state, complement);
    }
}
