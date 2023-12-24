package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StopListRequest;
import peaksoft.exceptions.AlreadyExistsException;
import peaksoft.models.MenuItem;
import peaksoft.models.StopList;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.StopListRepository;
import peaksoft.service.StopListService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StopListServImpl implements StopListService {

    private final MenuItemRepository menuItemRepository;
    private final StopListRepository stopListRepository;

    @Override
    public SimpleResponse save(StopListRequest stopListRequest) {
        MenuItem menuItem=menuItemRepository.findMenuItemByName(stopListRequest.menuName());

        Optional<StopList> existStopList=stopListRepository.getStopListsByMenuItem(menuItem);

        if(existStopList.isPresent()){
            throw new AlreadyExistsException("stopList already have been created");
        }else {
            StopList stopList=StopList.builder()
                    .date(stopListRequest.date())
                    .reason(stopListRequest.reason())
                    .build();

            stopList.setMenuItem(menuItem);
            menuItem.setStopList(stopList);
            stopListRepository.save(stopList);
        }
        return new SimpleResponse(HttpStatus.OK, "saved");
    }

    @Override
    public SimpleResponse delete(long id) {
        stopListRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "deleted");
    }
}
