package br.com.fiap.postech.orders.infrastructure.api;

import br.com.fiap.postech.orders.infrastructure.api.models.Customer;
import br.com.fiap.postech.orders.interfaces.dto.CustomerApiResponseExternalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
public class RestTemplateCustomerGateway implements CustomerGateway {

    private final RestTemplate restTemplate;
    @Value("${api.customers.url}")
    private String customerServiceUrl;

    public RestTemplateCustomerGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        final String url = customerServiceUrl + "/" + customerId.toString();
        return Objects.requireNonNull(restTemplate.getForObject(url, CustomerApiResponseExternalDTO.class)).toDomain();
    }
}
