package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class AuthorDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public void saveAuthor(Author entity)
    {
        entityManager.persist(entity);
    }

    public void updateAuthor(Author entity)
    {
        entityManager.merge(entity);
    }

    public Author findById(Long id)
    {
        return entityManager.find(Author.class, id);
    }

    public void delete(Author entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List<Author> findAll()
    {
        TypedQuery<Author> q = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return q.getResultList();
    }
}
