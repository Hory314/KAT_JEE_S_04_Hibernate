package pl.coderslab.Dao.Interfaces;

import pl.coderslab.Entity.Book;

import java.util.List;

public interface BookDao
{
    void saveBook(Book entity);

    void updateBook(Book entity);

    Book findById(Long id);

    void deleteBook(Book entity);

    List<Book> findAll();

    public List<Book> getRatingList(int rating);

    public List<Book> findAllPropositions();
}
