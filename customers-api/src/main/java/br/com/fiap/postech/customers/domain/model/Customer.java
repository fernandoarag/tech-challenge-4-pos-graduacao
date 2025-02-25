package br.com.fiap.postech.customers.domain.model;


public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cellPhone;
    private Address address;

    /**
     * Default constructor.
     */
    public Customer() {}

    public Customer(String name, String email, String phone, String cellPhone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.address = address;
    }

    public Customer(Long id, String name, String email, String phone, String cellPhone, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public Address getAddress() {
        return address;
    }

    public void updateCustomerWithoutEmail(final Long customerID, final Customer customer) {
        this.id = customerID;
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.cellPhone = customer.getCellPhone();
        this.address = customer.getAddress();
    }

}


