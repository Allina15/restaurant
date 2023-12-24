package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StopListRequest;

public interface StopListService {

    SimpleResponse save(StopListRequest stopListRequest);

    SimpleResponse delete(long id);
}
