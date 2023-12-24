package peaksoft.dto;

import java.time.ZonedDateTime;

public record StopListRequest(ZonedDateTime date,
                              String reason,
                              String menuName) {
}
