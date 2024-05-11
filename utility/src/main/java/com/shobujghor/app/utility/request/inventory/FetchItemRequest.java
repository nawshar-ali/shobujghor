package com.shobujghor.app.utility.request.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchItemRequest implements Serializable {
    @NotNull
    private String categoryName;

    @NotNull
    private String itemName;
}
