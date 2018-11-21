package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.BookDao;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Publisher;
import pl.coderslab.Validation.BookValidationGroup;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

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

    @GetMapping("/books/create")
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
    public String saveBook(@ModelAttribute("newBook") @Validated({BookValidationGroup.class}) Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "books/create";
        }

        bookDao.saveBook(book);
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


    @GetMapping("/books/edit/{id}")
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("newBook", bookDao.findById(id));
        return "/books/create";
    }

    @PostMapping("/books/edit/{id}")
    public String postUpdate(@ModelAttribute("newBook") @Validated({BookValidationGroup.class}) Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/books/create";
        }
        bookDao.updateBook(book);
        return "redirect:/books";
    }
}
