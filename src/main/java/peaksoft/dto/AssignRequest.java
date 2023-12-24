package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRequest {
    private long restaurantId;
    private long workersId;
    private String acceptOrReject;
}
