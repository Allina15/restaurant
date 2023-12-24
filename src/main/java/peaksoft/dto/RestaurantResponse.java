package peaksoft.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RestaurantResponse {
    private String name;
    private String location;
    private String restType;
    private String numberOfEmployees;
    private String service;

    public RestaurantResponse(String name, String location, String restType, String numberOfEmployees, String service) {
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }
}
