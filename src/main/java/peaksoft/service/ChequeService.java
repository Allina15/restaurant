package peaksoft.service;

import peaksoft.dto.ChequeRequest;
import peaksoft.dto.ChequeResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;

public interface ChequeService {

    ChequeResponse save(ChequeRequest chequeRequest);

    String countCheque(String userName);

    String restaurantCheque(String name);
}
