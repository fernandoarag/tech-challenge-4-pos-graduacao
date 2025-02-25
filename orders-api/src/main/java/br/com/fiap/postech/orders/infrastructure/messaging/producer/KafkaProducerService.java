package br.com.fiap.postech.orders.infrastructure.messaging.producer;

import br.com.fiap.postech.orders.infrastructure.messaging.OrderCreatedEvent;
import br.com.fiap.postech.orders.infrastructure.messaging.OrderDeliveredEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final StreamBridge streamBridge;

    public KafkaProducerService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        streamBridge.send("orders.created", orderCreatedEvent);
        log.info("✅ [orders-api] -> OrderCreatedEvent enviado para Kafka: {}", orderCreatedEvent);
    }

    public void sendOrderDeliveredEvent(OrderDeliveredEvent orderDeliveredEvent) {
        streamBridge.send("orders.delivered", orderDeliveredEvent);
        log.info("✅ [orders-api] -> OrderDeliveredEvent enviado para Kafka: {}", orderDeliveredEvent);
    }
}
