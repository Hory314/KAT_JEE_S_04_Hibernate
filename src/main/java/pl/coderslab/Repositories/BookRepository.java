package pl.coderslab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    // 2
    List<Book> findSomeExtraSuperBooksByTitleIgnoreCase(String title);

    List<Book> findBooksByCategory(Category category);

    List<Book> findBooksByCategoryId(Long categoryId);

    long countAllBooksByCategory(Category category);

    // 3
    List<Book> findBooksByAuthorsLastNameOrAuthorsFirstName(String authorLastName, String authorFirstName);

    List<Book> findBooksByPublisherName(String publisherName); // findAllByAuthors(Author author)

    List<Book> findBooksByRatingBetween(Double minRating, Double maxRating);

    List<Book> findFirstByCategoryNameOrderByCategoryNameAsc(String categoryName);

}
