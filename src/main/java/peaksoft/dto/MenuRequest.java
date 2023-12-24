package peaksoft.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.validations.PriceValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String name;
    private String image;
    @PriceValidator()
    private double price;
    private String description;
    private boolean isVegetarian;
    private String category;
    private long restId;
}
