package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.ChequeRequest;
import peaksoft.dto.ChequeResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Cheque;
import peaksoft.models.MenuItem;
import peaksoft.models.Restaurant;
import peaksoft.models.User;
import peaksoft.repositories.ChequeRepository;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChequeServImpl implements ChequeService {

    private final UserRepository userRepository;

    private final ChequeRepository chequeRepository;

    private final MenuItemRepository menuItemRepository;

    private final RestaurantRepository restaurantRepository;

    @Override
    public ChequeResponse save(ChequeRequest chequeRequest) {
        User user = userRepository.findUserByFirstNameAndLastName(chequeRequest.firstName(), chequeRequest.lastName()).orElseThrow(() -> new NotFoundException("User not found"));
        List<MenuItem> menuItems = menuItemRepository.searchMenu(chequeRequest.menuName());
        double average = 0;
        for (MenuItem me : menuItems) {
            average += me.getPrice();
        }
        double service = average / 10;
        double grandTotal = average + service;
        Cheque cheque = new Cheque();
        cheque.setUser(user);
        cheque.setCreatedAt(ZonedDateTime.now());
        cheque.setPriceAverage(average);
        cheque.setMenuItems(menuItems);
        chequeRepository.save(cheque);
        return new ChequeResponse(user.getFirstName(), user.getLastName(), menuItems, average, service, grandTotal);
    }

    @Override
    public String countCheque(String userName) {
        User user = userRepository.findUserByFirstName(userName).orElseThrow(() -> new NotFoundException("User not found"));
        LocalDate today = LocalDate.now();
        double count = 0;
        double sum = 0;
        for (Cheque ch : user.getCheques()) {
            ZonedDateTime chequeDate = ch.getCreatedAt();
            if (chequeDate != null && chequeDate.toLocalDate().isEqual(today)) {
                count++;
                sum += ch.getPriceAverage();

            }
        }
        return "Количество: " + count + "\n" + " Сумма: " + sum;
    }

    @Override
    public SimpleResponse delete(long id) {
        chequeRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "deleted");
    }

    @Override
    public String restaurantCheque(String name) {
        Restaurant restaurant = restaurantRepository.findByName(name);
        LocalDate today = LocalDate.now();
        if (restaurant == null) {
            throw new NotFoundException("Ресторан не найден");
        }
        double sum = restaurant.getUsers().stream()
                .flatMap(u -> u.getCheques().stream())
                .filter(ch -> {
                    ZonedDateTime chequeDate = ch.getCreatedAt();
                    return chequeDate != null && chequeDate.toLocalDate().isEqual(today);
                })
                .mapToDouble(Cheque::getPriceAverage)
                .sum();
            return "сумма чеков ресторана: " + sum+"\n"+"Дата: "+today;
    }
}
