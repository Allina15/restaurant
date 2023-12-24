package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.models.SubCategory;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponse {
    private List<SubCategory>subCategories;
}
