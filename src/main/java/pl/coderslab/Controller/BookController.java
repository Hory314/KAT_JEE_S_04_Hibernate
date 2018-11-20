package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.BookDao;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Publisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@Controller
public class BookController
{
    @Autowired
    private BookDao bookDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private AuthorDao authorDao;

    @GetMapping("/books")
    public String listOfBooks(Model model)
    {
        model.addAttribute("books", bookDao.findAll());
        return "/books/all";
    }

    @GetMapping("/books/create") // dzien2/czesc2
    public String book(HttpServletRequest request)
    {
        request.setAttribute("newBook", new Book());
        return "/books/create";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers()
    {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors()
    {
        return authorDao.findAll();
    }

    @PostMapping("/books/create")
    public String saveBook(Book book)
    {
        System.out.println(book.toString());

        bookDao.saveBook(book);
        System.out.println("id: " + book.getId());

        // return "redirect:/books/" + book.getId();
        return "redirect:/books";
    }


    @RequestMapping("/books/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return bookDao.findById(id).toString();
    }

    @RequestMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        Book bookToDel = bookDao.findById(id);
        bookDao.delete(bookToDel);
        return "redirect:/books";
    }

    @RequestMapping("/books/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model)
    {
        model.addAttribute("id", id);
        return "/books/del-confirm";
    }

    @RequestMapping("/books/add")
    @ResponseBody
    public String add()
    {
        Random generator = new Random();

        Book book = new Book();
        book.setTitle("Nowa ksiaza" + generator.nextInt(100000) + 1);
        //book.setAuthors(authorDao.findById(1L));
        book.setPublisher(publisherDao.findById(1L));
        book.setDescription("Super ksiazka");
        book.setRating(5.5);

        bookDao.saveBook(book);
        return "Dodano:\n" + book.toString();
    }

    @GetMapping("/books/edit/{id}")
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("newBook", bookDao.findById(id));
        return "/books/create";
    }

    @PostMapping("/books/edit/{id}")
    public String postUpdate(@ModelAttribute Book book)
    {
        bookDao.updateBook(book);
        return "redirect:/books";
    }
}
