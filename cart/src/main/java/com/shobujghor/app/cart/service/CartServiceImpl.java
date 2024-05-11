package com.shobujghor.app.cart.service;

import com.shobujghor.app.cart.api.InventoryClient;
import com.shobujghor.app.cart.repository.CartRepository;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.dto.CartItem;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.models.Cart;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.util.MathUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final InventoryClient inventoryClient;
    private final CartRepository cartRepository;
    private final ErrorHelperService errorHelperService;
    private final String CART_ID_PREFIX = "#CART-";

    @Override
    public AddToCartResponse addItemToCart(AddToCartRequest request) {
        var item = inventoryClient.getItem(FetchItemRequest.builder()
                .itemName(request.getItemName())
                .categoryName(request.getCategoryName())
                .build()
        );

        if (StringUtils.hasText(request.getCartId())) {
            fetchCartAndAddItem(request, item);
        } else {
            createNewCart(request, item);
        }


        return AddToCartResponse.builder().cartId(request.getCartId()).build();
    }

    private void createNewCart(AddToCartRequest request, ItemDto item) {
        // TODO update discount logic later
        var cartItem = CartItem.builder()
                .itemName(item.getName())
                .quantity(request.getQuantity())
                .totalPrice(MathUtil.multiplyDoubleString(request.getQuantity(), item.getPrice()))
                .totalDiscount("0.0")
                .actualPrice(MathUtil.multiplyDoubleString(request.getQuantity(), item.getPrice()))
                .build();
        var cartId = getCartId();

        var cart = Cart.builder()
                .id(cartId)
                .amountToBePaid(cartItem.getTotalPrice())
                .totalBillAmount(cartItem.getTotalPrice())
                .itemList(List.of(cartItem))
                .build();
        cartRepository.saveData(cart);
        request.setCartId(cartId);
    }

    private void fetchCartAndAddItem(AddToCartRequest request, ItemDto item) {
        cartRepository.getData(request.getCartId())
                .ifPresentOrElse(cart -> {
                    cart.getItemList().stream()
                            .forEach(cartItem -> {
                                if (item.getName().equals(cartItem.getItemName())) {
                                    var updatedQuantity = MathUtil.addIntegerString(request.getQuantity(), cartItem.getQuantity());
                                    var updatedPrice = MathUtil.addDoubleString(item.getPrice(), cartItem.getTotalPrice());

                                    // TODO: update discount logic later
                                    cart.setTotalBillAmount(updatedPrice);
                                    cartItem.setQuantity(updatedQuantity);
                                    cart.setAmountToBePaid(updatedPrice);
                                }
                            });
                    cartRepository.saveData(cart);
                }, () -> errorHelperService.buildExceptionFromCode(ErrorUtil.CART_NOT_FOUND));
    }

    private String getCartId() {
        return CART_ID_PREFIX + UUID.randomUUID().toString();
    }
}
