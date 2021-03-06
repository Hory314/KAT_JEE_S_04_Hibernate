package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Category;
import pl.coderslab.Entity.Publisher;
import pl.coderslab.Repositories.BookRepository;
import pl.coderslab.Repositories.CategoryRepository;
import pl.coderslab.Validation.BookValidationGroup;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Repository
public class BookController
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private AuthorDao authorDao;


    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/books")
    public String listOfBooks(Model model)
    {
        model.addAttribute("books", bookRepository.findAll());
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

    @ModelAttribute("categories")
    public List<Category> categories()
    {
        return categoryRepository.findAll();
    }

    @PostMapping("/books/create")
    public String saveBook(@ModelAttribute("newBook") @Validated({BookValidationGroup.class}) Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "books/create";
        }

        // bookRepository.saveBook(book); //myDao
        bookRepository.save(book);//SpringData
        return "redirect:/books";
    }


    @RequestMapping("/books/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return bookRepository.findById(id).toString();
    }

    @RequestMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        Book bookToDel = bookRepository.findById(id);
        bookRepository.deleteBook(bookToDel);
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
        model.addAttribute("newBook", bookRepository.findById(id));
        return "/books/create";
    }

    @PostMapping("/books/edit/{id}")
    public String postUpdate(@ModelAttribute("newBook") @Validated({BookValidationGroup.class}) Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/books/create";
        }
        bookRepository.updateBook(book);
        return "redirect:/books";
    }

    //////// DZIEN 4 //////////////


    //1,2
    @GetMapping("/books/categories")
    public String getCategoriesList(Model model)
    {
        List<Category> allCategories = categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        return "/books/categories";
    }

    @GetMapping("/books/categories/{id:[0-9]+}")
    public String getByCategory(@PathVariable Long id, Model model)
    {
        Category category = categoryRepository.findOne(id);
        List<Book> books = bookRepository.findBooksByCategory(category);
        model.addAttribute("books", books);
        return "/books/all";
    }

    // 3
    @GetMapping("/books/author/{name}")
    public String getByAuthorName(@PathVariable String name, Model model)
    {
        model.addAttribute("books", bookRepository.findBooksByAuthorsLastNameOrAuthorsFirstName(name, name));
        return "/books/all";
    }

    @GetMapping("/books/publisher/{name}")
    public String getByPublisherName(@PathVariable String name, Model model)
    {
//        model.addAttribute("books", bookRepository.findBooksByPublisherName(name));
        model.addAttribute("books", bookRepository.getPublishersBooks(name));
        return "/books/all";
    }

    @GetMapping("/books/rating/{min:[0-9]*\\.*[0-9]+}/{max:[0-9]*\\.*[0-9]+}")
    public String getByRating(@PathVariable Double min, @PathVariable Double max, Model model)
    {
        //  model.addAttribute("books", bookRepository.findBooksByRatingBetween(min, max));
        model.addAttribute("books", bookRepository.getRatingBetween(min, max));
        return "/books/all";
    }

    @GetMapping("/books/category/{name}")
    public String getFirstByCategoryName(@PathVariable String name, Model model)
    {

//        model.addAttribute("books", bookRepository.findFirstByCategoryNameOrderByTitleAsc(name));
        model.addAttribute("books", bookRepository.getOneByCategory(name));
        return "/books/all";
    }

    // RESET RATING//
    @GetMapping("/books/admin/reset_ratings/{rating:[0-9]*\\.*[0-9]+}")
    public String resetRatings(@PathVariable Double rating)
    {
        bookRepository.resetRating(rating);
        return "redirect:/books";
    }
}
