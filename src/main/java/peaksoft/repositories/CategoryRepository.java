package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.models.Category;
import peaksoft.models.SubCategory;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query("SELECT c FROM Category c JOIN c.subCategories s WHERE s = :subCategories")
    Category findCategoryBySubCategory(@Param("subCategories") SubCategory subCategories);

}
