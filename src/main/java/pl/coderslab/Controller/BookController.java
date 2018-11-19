package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Dao.BookDao;
import pl.coderslab.Entity.Book;

import java.util.Random;

@Controller
public class BookController
{
    @Autowired
    private BookDao bookDao;

    @RequestMapping("/books/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return bookDao.findById(id).toString();
    }

    @RequestMapping("/books/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id)
    {
        Book bookToDel = bookDao.findById(id);
        bookDao.delete(bookToDel);
        return "Usunięto:\n" + bookToDel.toString();
    }

    @RequestMapping("/books/add")
    @ResponseBody
    public String add()
    {
        Random generator = new Random();

        Book book = new Book();
        book.setTitle("Nowa ksiaza" + generator.nextInt(100000) + 1);
        book.setAuthor("Autor nowy");
        book.setPublisher("Nowe wydawnictwo");
        book.setDescription("Super ksiazka");
        book.setRating(5.5);

        bookDao.saveBook(book);
        return "Dodano:\n" + book.toString();
    }

    @RequestMapping("/books/edit/{id}")
    @ResponseBody
    public String update(@PathVariable Long id)
    {
        Random generator = new Random();
        Book book = bookDao.findById(id);
        book.setTitle("Zmieniony tytuł" + generator.nextInt(10000) + 1);
        book.setRating(1.0);
        book.setDescription("Jedanak słaba...");
        bookDao.updateBook(book);

        return bookDao.findById(id).toString();
    }
}
