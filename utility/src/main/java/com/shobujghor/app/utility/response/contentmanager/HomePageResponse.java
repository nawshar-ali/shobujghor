package com.shobujghor.app.utility.response.contentmanager;

import com.shobujghor.app.utility.dto.CategoryDto;
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
public class HomePageResponse implements Serializable {
    @Builder.Default
    private List<CategoryDto> categories = new ArrayList<>();
}
