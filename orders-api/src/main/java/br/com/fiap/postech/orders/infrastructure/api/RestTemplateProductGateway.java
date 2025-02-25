package br.com.fiap.postech.orders.infrastructure.api;

import br.com.fiap.postech.orders.infrastructure.api.models.Product;
import br.com.fiap.postech.orders.interfaces.dto.QuantityItemResquestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateProductGateway implements ProductGateway {

    private final RestTemplate restTemplate;
    @Value("${api.products.url}")
    private String productServiceUrl;

    public RestTemplateProductGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {
        String url = productServiceUrl + "/" + productId;
        return restTemplate.getForObject(url, Product.class); // Faz uma requisição GET
    }

    @Override
    public boolean isInStock(Long productId, int quantity) {
        String url = productServiceUrl + "/" + productId;
        Product response = restTemplate.getForObject(url, Product.class); // Faz uma requisição GET
        return Boolean.TRUE.equals(response != null && response.isInStock(quantity)); // Retorna false se for null
    }

    @Override
    public void subtractStocks(final List<QuantityItemResquestDTO> quantityItemRequestDTO) {
        String url = productServiceUrl + "/update-stock";
        restTemplate.patchForObject(url, quantityItemRequestDTO, Void.class);
    }
}