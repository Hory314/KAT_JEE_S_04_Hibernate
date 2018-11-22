package pl.coderslab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Entity.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>
{
    List<Publisher> findFirstByNip(String nip);

    List<Publisher> findFirstByRegon(String regon);
}
