package br.com.fiap.postech.orders.application.usecases;

import br.com.fiap.postech.orders.domain.entities.Address;
import br.com.fiap.postech.orders.domain.entities.Order;
import br.com.fiap.postech.orders.domain.entities.OrderItem;
import br.com.fiap.postech.orders.domain.enums.OrderStatus;
import br.com.fiap.postech.orders.domain.enums.PaymentMethod;
import br.com.fiap.postech.orders.infrastructure.api.CustomerGateway;
import br.com.fiap.postech.orders.infrastructure.api.ProductGateway;
import br.com.fiap.postech.orders.infrastructure.exception.InvalidStatusException;
import br.com.fiap.postech.orders.infrastructure.gateway.impl.OrderRepositoryGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateOrderStatusUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private OrderRepositoryGatewayImpl orderRepositoryGateway;

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private UpdateOrderStatusUseCase updateOrderStatusUseCase;

    private static final Long customerId = 1L;
    private static final Long productId = 1L;
    private static final Address address = new Address("12345", "Main St", "100", "Downtown", "Metropolis", "NY", "Apt 1");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateStatusFromOpenToPaid() {
        // Criação de IDs aleatórios para simular um cliente e um produto

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.OPEN);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = updateOrderStatusUseCase.execute(order.getId(), OrderStatus.PAID);

        // Assert
        assertEquals(OrderStatus.PAID, updatedOrder.getStatus());
        verify(orderRepositoryGateway, times(1)).save(order);
    }

    @Test
    void testUpdateStatusFromPaidToProcessing() {
        // Criação de IDs aleatórios para simular um cliente e um produto

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.PAID);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = updateOrderStatusUseCase.execute(order.getId(), OrderStatus.PROCESSING);

        // Assert
        assertEquals(OrderStatus.PROCESSING, updatedOrder.getStatus());
        verify(orderRepositoryGateway, times(1)).save(order);
    }

    @Test
    void testUpdateStatusFromProcessingToShipped() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());


        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = updateOrderStatusUseCase.execute(order.getId(), OrderStatus.SHIPPED);

        // Assert
        assertEquals(OrderStatus.SHIPPED, updatedOrder.getStatus());
        verify(orderRepositoryGateway, times(1)).save(order);
    }

    @Test
    void testUpdateStatusFromShippedToDelivered() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.SHIPPED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = updateOrderStatusUseCase.execute(order.getId(), OrderStatus.DELIVERED);

        // Assert
        assertEquals(OrderStatus.DELIVERED, updatedOrder.getStatus());
        verify(orderRepositoryGateway, times(1)).save(order);
    }

    @Test
    void testUpdateStatusFromDeliveredToReturned() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.DELIVERED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Assert
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> updateOrderStatusUseCase.execute(orderId, OrderStatus.OPEN));
    }

    @Test
    void testUpdateStatusFromOpenToCanceled() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.OPEN);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = updateOrderStatusUseCase.execute(order.getId(), OrderStatus.CANCELED);

        // Assert
        assertEquals(OrderStatus.CANCELED, updatedOrder.getStatus());
        verify(orderRepositoryGateway, times(1)).save(order);
    }

    @Test
    void testUpdateStatusFromCanceledToOpen() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.CANCELED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);
        when(orderRepositoryGateway.save(order)).thenReturn(order);

        // Assert
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> updateOrderStatusUseCase.execute(orderId, OrderStatus.OPEN));
    }

    @Test
    void testUpdateStatusWithSameStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.OPEN);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Act & Assert
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.OPEN);
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromDeliveredToShippedThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.DELIVERED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Act & Assert
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.SHIPPED);
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromReturnedThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.RETURNED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Act & Assert
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.OPEN);
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromOpenToInvalidStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.OPEN);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Teste para transição inválida de OPEN para qualquer status diferente de PAID ou CANCELED
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.PROCESSING); // Transição inválida
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromPaidToInvalidStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.PAID);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Teste para transição inválida de PAID para qualquer status diferente de PROCESSING ou CANCELED
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.OPEN); // Transição inválida
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromProcessingToInvalidStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Teste para transição inválida de PROCESSING para qualquer status diferente de SHIPPED ou CANCELED
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.DELIVERED); // Transição inválida
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusFromShippedToInvalidStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.SHIPPED);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Teste para transição inválida de SHIPPED para qualquer status diferente de DELIVERED
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, OrderStatus.PROCESSING); // Transição inválida
        });

        verify(orderRepositoryGateway, never()).save(order);
    }

    @Test
    void testUpdateStatusWithInvalidStatusThrowsException() {

        // Criação do pedido real
        Order order = new Order();
        order.setStatus(OrderStatus.OPEN);
        order.setCustomerId(customerId);
        // Criação de um item de pedido real
        OrderItem item = new OrderItem(UUID.randomUUID(), productId, 2, BigDecimal.valueOf(50.0), order);
        order.addItem(item);
        order.setDeliveryAddress(address);
        order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        when(orderRepositoryGateway.findById(order.getId())).thenReturn(order);

        // Teste para status inválido
        UUID orderId = order.getId();
        assertThrows(InvalidStatusException.class, () -> {
            updateOrderStatusUseCase.execute(orderId, null); // Status inválido
        });

        verify(orderRepositoryGateway, never()).save(order);
    }
}