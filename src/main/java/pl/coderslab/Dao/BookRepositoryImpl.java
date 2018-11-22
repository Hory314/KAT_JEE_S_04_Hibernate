package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Dao.Interfaces.BookDao;
import pl.coderslab.Dao.Interfaces.BookDaoAdvQueriesPack;
import pl.coderslab.Entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class BookRepositoryImpl implements BookDao, BookDaoAdvQueriesPack
{
    @PersistenceContext // wstrzyknięcie entityManagera
    private EntityManager entityManager;

    @Override
    public void saveBook(Book entity)
    {
        entityManager.persist(entity);
    }

    @Override

    public void updateBook(Book entity)
    {
        entityManager.merge(entity);
    }

    @Override

    public Book findById(Long id)
    {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void deleteBook(Book entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public List<Book> findAll()
    {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    @Override
    public List<Book> getRatingList(int rating)
    {
        Query queryp = entityManager.createQuery("SELECT b FROM Book b where b.rating >:rating");
        queryp.setParameter("rating", rating);
        return queryp.getResultList();

    }

    @Override
    public List<Book> findAllPropositions()
    {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.proposition = ?1");
        query.setParameter(1, true); // o: 1 = true (w bazie tinyint)
        return query.getResultList();
    }

    // ADV OVERRIDE
    @Override
    public void resetRating(Double rating) // ustawia sredni rating na 5.0
    {
        Query query = entityManager.createQuery("UPDATE Book b SET b.rating = ?1"); // where is ommited for update all rows
        query.setParameter(1, rating);
        query.executeUpdate();
    }
}
