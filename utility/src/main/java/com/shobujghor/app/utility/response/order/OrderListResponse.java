package com.shobujghor.app.utility.response.order;

import com.shobujghor.app.utility.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse implements Serializable {

    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
