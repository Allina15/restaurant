package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.models.Category;
import peaksoft.models.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("select s from SubCategory s join s.category c where c.name = :category order by s.name asc")
    List<SubCategory> getSubCategoriesByCategoryName(String category);

    SubCategory findByName(String name);
}
