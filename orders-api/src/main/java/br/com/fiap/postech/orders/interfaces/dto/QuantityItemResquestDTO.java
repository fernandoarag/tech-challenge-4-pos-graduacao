package br.com.fiap.postech.orders.interfaces.dto;

import br.com.fiap.postech.orders.domain.entities.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

@Slf4j
public record QuantityItemResquestDTO(

        @NotNull(message = "O ID do produto é obrigatório")
        @Validated Long productId,

        @NotEmpty(message = "A quantidade não pode estar vazia")
        @Validated int quantity

) {

    public static QuantityItemResquestDTO fromValues(Long productId, int quantity) {
        return new QuantityItemResquestDTO(productId, quantity);
    }

}