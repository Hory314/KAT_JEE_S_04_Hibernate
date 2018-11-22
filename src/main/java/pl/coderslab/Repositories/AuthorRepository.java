package pl.coderslab.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    List<Author> findFirstAuthorByEmail(String email);

    List<Author> findFirstAuthorByPesel(String pesel);

    List<Author> findAuthorsByLastName(String lastName);

}
