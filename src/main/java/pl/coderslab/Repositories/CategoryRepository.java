package pl.coderslab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
