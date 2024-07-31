package com.shobujghor.app.order.service;

import com.shobujghor.app.order.repository.OrderRepository;
import com.shobujghor.app.utility.models.Order;
import com.shobujghor.app.utility.request.order.PlaceOrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderServiceImplTest {

    @Mock
    private OrderRepository mockOrderRepository;

    private OrderServiceImpl orderServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        orderServiceImplUnderTest = new OrderServiceImpl(mockOrderRepository);
    }

    @Test
    void testPlaceOrder() {
        // Setup
        final PlaceOrderRequest request = PlaceOrderRequest.builder()
                .orderId("id")
                .customerEmail("customerEmail")
                .items(List.of("value"))
                .originalPrice("originalPrice")
                .discountPrice("discountPrice")
                .actualPrice("actualPrice")
                .deliveryAddress("deliveryAddress")
                .orderDateTime("orderDateTime")
                .build();

        // Run the test
        orderServiceImplUnderTest.placeOrder(request);

        // Verify the results
        verify(mockOrderRepository).saveData(Order.builder()
                .customerEmail("customerEmail")
                .id("id")
                .originalPrice("originalPrice")
                .discountPrice("discountPrice")
                .actualPrice("actualPrice")
                .orderDateTime("orderDateTime")
                .deliveryAddress("deliveryAddress")
                .items(List.of("value"))
                .build());
    }
}
