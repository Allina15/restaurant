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

    public SubCategoryRequest(String name,String newName) {
        this.name = name;
        this.newName = newName;
    }

    public SubCategoryRequest(String name, String category, String newName) {
        this.name = name;
        this.category = category;
        this.newName = newName;
    }
}
