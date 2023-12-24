package peaksoft.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.models.Category;

@Data
@NoArgsConstructor
public class SubCategoryRequest {
    private String name;
    private String category;
    private String newName;
}
