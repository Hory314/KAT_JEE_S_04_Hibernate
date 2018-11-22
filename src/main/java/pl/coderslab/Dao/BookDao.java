package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class BookDao
{
    @PersistenceContext // wstrzyknięcie entityManagera
    private EntityManager entityManager;

    public void saveBook(Book entity)
    {
        entityManager.persist(entity);
    }

    public void updateBook(Book entity)
    {
        entityManager.merge(entity);
    }

    public Book findById(Long id)
    {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List<Book> findAll()
    {
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        return query.getResultList();
    }

    public List<Book> getRatingList(int rating)
    {
        Query queryp = entityManager.createQuery("SELECT b FROM Book b where rating >:rating");
        queryp.setParameter("rating", rating);
        return queryp.getResultList();

    }

    public List<Book> findAllPropositions()
    {
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE proposition = ?1");
        query.setParameter(1,true); // o: 1 = true (w bazie tinyint)
        return query.getResultList();
    }

}
