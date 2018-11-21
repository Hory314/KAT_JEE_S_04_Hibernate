package pl.coderslab.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.Validation.BookGroupValidation;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 5, groups = BookGroupValidation.class)
    @NotNull(groups = Default.class)
    @NotEmpty(groups = Default.class)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull(groups = BookGroupValidation.class)
    @Size(min = 1, groups = BookGroupValidation.class)
    private List<Author> authors;

    @NotNull(groups = BookGroupValidation.class)
    @ManyToOne
    private Publisher publisher;

    @Column(columnDefinition = "TEXT")
    @Size(max = 600, groups = BookGroupValidation.class)
    @NotNull(groups = Default.class)
    @NotEmpty(groups = Default.class)
    private String description;

    @Column(scale = 2, precision = 4)
    @Min(value = 1, groups = BookGroupValidation.class)
    @Max(value = 10, groups = BookGroupValidation.class)
    private Double rating;

    @Min(value = 2, message = "Książka musi mieć przynajmniej 2 strony", groups = BookGroupValidation.class)
    private Integer pages;

    @Null(groups = BookGroupValidation.class)
    private Boolean proposition;

    public Book()
    {
    }

    public Boolean getProposition()
    {
        return proposition;
    }

    public void setProposition(Boolean proposition)
    {
        this.proposition = proposition;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }

    public Publisher getPublisher()
    {
        return publisher;
    }

    public void setPublisher(Publisher publisher)
    {
        this.publisher = publisher;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public Integer getPages()
    {
        return pages;
    }

    public void setPages(Integer pages)
    {
        this.pages = pages;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
