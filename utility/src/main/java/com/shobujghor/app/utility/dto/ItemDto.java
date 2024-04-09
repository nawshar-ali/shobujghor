package com.shobujghor.app.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {
    private String name;
    private String shortDescription;
    private String longDescription;
    private String logoUrl;
    private String price;
}
