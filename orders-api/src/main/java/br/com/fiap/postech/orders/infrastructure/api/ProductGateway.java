package br.com.fiap.postech.orders.infrastructure.api;

import br.com.fiap.postech.orders.infrastructure.api.models.Product;
import br.com.fiap.postech.orders.interfaces.dto.QuantityItemResquestDTO;

import java.util.List;

public interface ProductGateway {

    Product getProductById(Long productId);

    boolean isInStock(Long productId, int quantity);

    void subtractStocks(final List<QuantityItemResquestDTO> quantityItemRequestDTO);
}
