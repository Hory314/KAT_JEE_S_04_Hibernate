package pl.coderslab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.Dao.Interfaces.BookDao;
import pl.coderslab.Dao.Interfaces.BookDaoAdvQueriesPack;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>,
        BookDao,
        BookDaoAdvQueriesPack
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

    List<Book> findFirstByCategoryNameOrderByTitleAsc(String categoryName);

    // cz. 2 - zap. szczegolowe 1)
    @Query("select b from Book b where b.title = ?1")
    List<Book> getBookBy(String title); // nazwa obojetna bo jest @Query

    @Query("select b from Book b where b.category = :cat")
    List<Book> getBookBy(@Param("cat") Category category);

    // 2)
    @Query("select b from Book b where b.rating between ?1 AND ?2")
    List<Book> getRatingBetween(Double minRating, Double maxRating);

    @Query("select b from Book b where b.publisher.name = ?1")
    List<Book> getPublishersBooks(String publisherName);

    @Query(value = "select * from books\n" +
            "join categories c on books.category_id = c.id\n" +
            "where c.name = ?1\n" +
            "order by title asc\n" +
            "limit 1", nativeQuery = true)
    List<Book> getOneByCategory(String categoryName);
}
