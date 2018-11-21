package pl.coderslab.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.coderslab.Validation.Mature;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @NotNull
    @NotEmpty
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotNull
    @NotEmpty
    private String lastName;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
    //@PESEL
    private String pesel;
    //@Email
    private String email;
    @Column(name = "year_of_birth")
    @Mature
    private Integer yearOfBirth;

    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public String getPesel()
    {
        return pesel;
    }

    public void setPesel(String pesel)
    {
        this.pesel = pesel;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getYearOfBirth()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
