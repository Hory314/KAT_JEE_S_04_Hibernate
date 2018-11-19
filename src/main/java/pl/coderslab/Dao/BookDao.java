package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
