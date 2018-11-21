package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.BookDao;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Book;
import pl.coderslab.Entity.Publisher;
import pl.coderslab.Validation.BookValidationGroup;
import pl.coderslab.Validation.PropositionValidationGroup;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/propositions")
public class PropositionController
{
    @Autowired
    private BookDao bookDao;

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private AuthorDao authorDao;

    @ModelAttribute("publishers") // potem do @ControllerAdvice(r)?
    public List<Publisher> publishers()
    {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors()
    {
        return authorDao.findAll();
    }

    // SHOW ALL
    @RequestMapping
    public String showAllPropositions(Model model)
    {
        model.addAttribute("books", bookDao.findAllPropositions());
        return "/books/all"; // korzystam z tego samego widoku
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model)
    {
        Book book = new Book();

        model.addAttribute("newProposition", book);
        return "/propositions/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("newProposition") @Validated({PropositionValidationGroup.class}) Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/propositions/create";
        }
        book.setProposition(true); // ustawianie true (bo nie ma hiddena)
        bookDao.saveBook(book);
        return "redirect:/propositions";
    }
    // UPDATE

    // DELETE
}
