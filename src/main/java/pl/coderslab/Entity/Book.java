package pl.coderslab.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.Validation.BookValidationGroup;
import pl.coderslab.Validation.PropositionValidationGroup;

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
    @Size(min = 5, message = "Tytuł musi zawierać co najmniej 5 znaków", groups = {PropositionValidationGroup.class, BookValidationGroup.class})
    @NotNull(message = "Tytuł nie może być pusty", groups = {PropositionValidationGroup.class, BookValidationGroup.class})
    @NotEmpty(message = "Wpisz tytuł", groups = {PropositionValidationGroup.class, BookValidationGroup.class})
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull(message = "Autorzy nie mogą być puści", groups = {BookValidationGroup.class})
    @Size(min = 1, message = "Musisz wybrać co najmniej jednego autora", groups = BookValidationGroup.class)
    private List<Author> authors;

    @NotNull(message = "Musisz wybrać wydawnictwo", groups = BookValidationGroup.class)
    @ManyToOne
    private Publisher publisher;

    @Column(columnDefinition = "TEXT")
    @Size(max = 600, message = "Opis nie może zawierać więcej niż 600 znaków", groups = {PropositionValidationGroup.class, BookValidationGroup.class})
    @NotEmpty(message = "Wpisz opis", groups = {PropositionValidationGroup.class, BookValidationGroup.class})
    private String description;

    @Column(scale = 2, precision = 4)
    @Min(value = 1, message = "Ocena musi być w skali od 1 do 10", groups = {BookValidationGroup.class, PropositionValidationGroup.class})
    @Max(value = 10, message = "Ocena musi być w skali od 1 do 10", groups = {BookValidationGroup.class, PropositionValidationGroup.class})
    private Double rating;

    @Min(value = 2, message = "Książka musi mieć przynajmniej 2 strony", groups = {BookValidationGroup.class, PropositionValidationGroup.class})
    private Integer pages;

    @AssertTrue(groups = PropositionValidationGroup.class) // musi byc true dla Proposition
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
