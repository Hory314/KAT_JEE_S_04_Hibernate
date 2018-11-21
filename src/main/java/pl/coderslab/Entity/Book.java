package pl.coderslab.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 5)
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    @Size(min = 1)
    private List<Author> authors;
    @ManyToOne
    @NotNull
    private Publisher publisher;
    @Column(columnDefinition = "TEXT")
    @Size(max = 600)
    private String description;
    @Column(scale = 2, precision = 4)
    @Min(1)
    @Max(10)
    private Double rating;
    @Min(value = 2, message = "Książka musi mieć przynajmniej 2 strony")
    private Integer pages;

    public Book()
    {
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
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher=" + publisher +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", pages=" + pages +
                '}';
    }
}
