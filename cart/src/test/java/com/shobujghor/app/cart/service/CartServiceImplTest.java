package com.shobujghor.app.cart.service;

import com.google.gson.Gson;
import com.shobujghor.app.cart.api.InventoryClient;
import com.shobujghor.app.cart.repository.CartRepository;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.dto.CartItem;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.exception.ApplicationException;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.models.Cart;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.cart.CheckoutRequest;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.response.cart.CheckoutResponse;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private InventoryClient mockInventoryClient;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private ErrorHelperService mockErrorHelperService;
    @Mock
    private SqsTemplate mockSqsTemplate;

    private CartServiceImpl cartServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        cartServiceImplUnderTest = new CartServiceImpl(mockInventoryClient, mockCartRepository, mockErrorHelperService,
                mockSqsTemplate, new Gson());
        ReflectionTestUtils.setField(cartServiceImplUnderTest, "orderQueue", "orderQueue");
    }

    @Test
    void testAddItemToCart() {
        // Setup
        final AddToCartRequest request = AddToCartRequest.builder()
                .itemName("itemName")
                .categoryName("categoryName")
                .quantity("1")
                .cartId("cartId")
                .build();
        final AddToCartResponse expectedResult = AddToCartResponse.builder()
                .cartId("cartId")
                .build();

        // Configure InventoryClient.getItem(...).
        final ItemDto itemDto = ItemDto.builder()
                .name("itemName")
                .price("20.0")
                .build();
        when(mockInventoryClient.getItem(FetchItemRequest.builder()
                .categoryName("categoryName")
                .itemName("itemName")
                .build())).thenReturn(itemDto);

        // Configure CartRepository.getData(...).
        final Optional<Cart> cart = Optional.of(Cart.builder()
                .id("cartId")
                .itemList(List.of(CartItem.builder()
                        .itemName("itemName")
                        .quantity("2")
                        .totalPrice("200.0")
                        .totalDiscount("10.0")
                        .actualPrice("190.0")
                        .build()))
                .totalBillAmount("actualPrice")
                .totalDiscountAmount("discountPrice")
                .amountToBePaid("actualPrice")
                .build());
        when(mockCartRepository.getData("cartId")).thenReturn(cart);

        // Run the test
        final AddToCartResponse result = cartServiceImplUnderTest.addItemToCart(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockCartRepository).saveData(Cart.builder()
                .id("cartId")
                .itemList(List.of(CartItem.builder()
                        .itemName("itemName")
                        .quantity("3")
                        .totalPrice("220.0")
                        .totalDiscount("10.0")
                        .actualPrice("210.0")
                        .build()))
                .totalBillAmount("220.0")
                .totalDiscountAmount("10.0")
                .amountToBePaid("210.0")
                .build());
    }

    @Test
    void testAddItemToCart_CartRepositoryGetDataReturnsAbsent() {
        // Setup
        final AddToCartRequest request = AddToCartRequest.builder()
                .itemName("itemName")
                .categoryName("categoryName")
                .quantity("quantity")
                .cartId("cartId")
                .build();
        final AddToCartResponse expectedResult = AddToCartResponse.builder()
                .cartId("cartId")
                .build();

        // Configure InventoryClient.getItem(...).
        final ItemDto itemDto = ItemDto.builder()
                .name("itemName")
                .price("price")
                .build();
        when(mockInventoryClient.getItem(FetchItemRequest.builder()
                .categoryName("categoryName")
                .itemName("itemName")
                .build())).thenReturn(itemDto);

        when(mockCartRepository.getData("cartId")).thenReturn(Optional.empty());

        // Run the test
        final AddToCartResponse result = cartServiceImplUnderTest.addItemToCart(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockErrorHelperService).buildExceptionFromCode("CART_NOT_FOUND");
    }

    @Test
    void testCheckout() {
        // Setup
        final CheckoutRequest request = CheckoutRequest.builder()
                .customerEmail("customerEmail")
                .cartId("cartId")
                .deliveryAddress("deliveryAddress")
                .build();
        final CheckoutResponse expectedResult = CheckoutResponse.builder()
                .success(true)
                .orderId("#ORDERd")
                .build();

        // Configure CartRepository.getData(...).
        final Optional<Cart> cart = Optional.of(Cart.builder()
                .id("cartId")
                .itemList(List.of(CartItem.builder()
                        .itemName("itemName")
                        .quantity("quantity")
                        .totalPrice("actualPrice")
                        .totalDiscount("totalDiscount")
                        .actualPrice("actualPrice")
                        .build()))
                .totalBillAmount("actualPrice")
                .totalDiscountAmount("discountPrice")
                .amountToBePaid("actualPrice")
                .build());
        when(mockCartRepository.getData("cartId")).thenReturn(cart);

        // Run the test
        final CheckoutResponse result = cartServiceImplUnderTest.checkout(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockSqsTemplate).sendAsync(any(Consumer.class));
        verify(mockCartRepository).deleteData(Cart.builder()
                .id("cartId")
                .itemList(List.of(CartItem.builder()
                        .itemName("itemName")
                        .quantity("quantity")
                        .totalPrice("actualPrice")
                        .totalDiscount("totalDiscount")
                        .actualPrice("actualPrice")
                        .build()))
                .totalBillAmount("actualPrice")
                .totalDiscountAmount("discountPrice")
                .amountToBePaid("actualPrice")
                .build());
    }

    @Test
    void testCheckout_CartRepositoryGetDataReturnsAbsent() {
        // Setup
        final CheckoutRequest request = CheckoutRequest.builder()
                .customerEmail("customerEmail")
                .cartId("cartId")
                .deliveryAddress("deliveryAddress")
                .build();
        when(mockCartRepository.getData("cartId")).thenReturn(Optional.empty());

        // Configure ErrorHelperService.buildExceptionFromCode(...).
        final ApplicationException applicationException = new ApplicationException("errorCode", "message",
                HttpStatus.OK);
        when(mockErrorHelperService.buildExceptionFromCode(ErrorUtil.CART_NOT_FOUND)).thenReturn(applicationException);

        // Run the test
        assertThatThrownBy(() -> cartServiceImplUnderTest.checkout(request)).isInstanceOf(ApplicationException.class);
    }
}
