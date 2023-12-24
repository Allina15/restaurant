package peaksoft.dto;

import java.util.List;

public record ChequeRequest(
        List<String> menuName,
        String firstName,
        String lastName
) {
}
