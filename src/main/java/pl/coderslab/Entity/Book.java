package pl.coderslab.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @ManyToMany
    private List<Author> authors;
    @ManyToOne
    private Publisher publisher;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(scale = 2, precision = 4)
    private Double rating;

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

    public void setAuthors(Author author)
    {
        this.authors.add(author);
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

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher=" + publisher +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
