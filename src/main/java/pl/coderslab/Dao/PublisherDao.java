package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PublisherDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher entity)
    {
        entityManager.persist(entity);
    }

    public void updatePublisher(Publisher entity)
    {
        entityManager.merge(entity);
    }

    public Publisher findById(Long id)
    {
        return entityManager.find(Publisher.class, id);
    }

    public void delete(Publisher entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
