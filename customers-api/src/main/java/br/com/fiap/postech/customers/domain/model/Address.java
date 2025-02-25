package br.com.fiap.postech.customers.domain.model;

public class Address {

    private final String zipCode;
    private final String name;
    private final String addressNumber;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String complement;

    public Address(String zipCode, String name, String addressNumber, String neighborhood, String city, String state, String complement) {
        this.zipCode = zipCode;
        this.name = name;
        this.addressNumber = addressNumber;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getComplement() {
        return complement;
    }
}