package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PersonDetailsDao
{
    @PersistenceContext // wstrzyknięcie entityManagera
    private EntityManager entityManager;

    public void save(PersonDetails entity)
    {
        entityManager.persist(entity);
    }

    public void update(PersonDetails entity)
    {
        entityManager.merge(entity);
    }

    public PersonDetails findById(Long id)
    {
        return entityManager.find(PersonDetails.class, id);
    }

    public void delete(PersonDetails entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
