package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.models.MenuItem;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChequeResponse {
    private String firstName;
    private String lastName;
    private List<MenuItem> menuName;
    private double averagePrice;
    private double service;
    private double grandTotal;

}
