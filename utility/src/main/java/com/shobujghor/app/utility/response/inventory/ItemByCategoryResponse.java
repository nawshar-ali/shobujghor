package com.shobujghor.app.utility.response.inventory;

import com.shobujghor.app.utility.dto.ItemDto;
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
public class ItemByCategoryResponse implements Serializable {
    @Builder.Default
    private List<ItemDto> items = new ArrayList<>();
}
