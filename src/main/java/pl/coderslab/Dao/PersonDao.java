package pl.coderslab.Dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.Entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class PersonDao
{
    @PersistenceContext // wstrzyknięcie entityManagera
    private EntityManager entityManager;

    public void save(Person entity)
    {
        entityManager.persist(entity);
    }

    public void update(Person entity)
    {
        entityManager.merge(entity);
    }

    public Person findById(Long id)
    {
        return entityManager.find(Person.class, id);
    }

    public void delete(Person entity)
    {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
