package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.MenuItem;
import peaksoft.models.StopList;

import java.util.Optional;

public interface StopListRepository extends JpaRepository<StopList, Long> {
    Optional<StopList> getStopListsByMenuItem(MenuItem menuItem);
}
