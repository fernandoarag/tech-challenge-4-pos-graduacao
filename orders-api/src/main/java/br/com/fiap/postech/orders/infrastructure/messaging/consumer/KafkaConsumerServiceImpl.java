package br.com.fiap.postech.orders.infrastructure.messaging.consumer;

import br.com.fiap.postech.orders.application.usecases.UpdateOrderStatusUseCase;
import br.com.fiap.postech.orders.infrastructure.messaging.OrderDeliveredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class KafkaConsumerServiceImpl {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public KafkaConsumerServiceImpl(UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @Bean
    public Consumer<OrderDeliveredEvent> orderDeliveredProcessor() {
        return event -> {
            // Aqui você pode chamar um serviço para atualizar o status no banco de dados
            updateOrderStatusUseCase.execute(event.getOrderId(), event.getOrderStatus());
            log.info("✅ [orders-api] -> Pedido entregue: {}", event);
            log.info("Class={}, Method={}, Message received: {}", "KafkaConsumerService", "orderCreatedProcessor", event);
        };
    }
}