package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Entity.Author;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller

public class AuthorController
{
    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/authors")
    public String showAuthors(Model model)
    {
        model.addAttribute("authors", authorDao.findAll());
        return "/authors/all";
    }

    @GetMapping("/authors/create") // dzien2/czesc2
    public String authors(HttpServletRequest request)
    {
        request.setAttribute("newAuthor", new Author());
        return "/authors/create";
    }

    @PostMapping("/authors/create")
    public String saveAuthor(@ModelAttribute Author author)
    {
        authorDao.saveAuthor(author);
        return "redirect:/authors";
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return authorDao.findById(id).toString();
    }

    @RequestMapping("/authors/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model)
    {
        model.addAttribute("id", id);
        return "/authors/del-confirm";
    }

    @RequestMapping("/authors/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        Author authorToDel = authorDao.findById(id);
        authorDao.delete(authorToDel);
        return "redirect:/authors";
    }


    @GetMapping("/authors/edit/{id}")
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("newAuthor", authorDao.findById(id));
        return "/authors/create";
    }

    @PostMapping("/authors/edit/{id}")
    public String postUpdate(@ModelAttribute Author author)
    {
        authorDao.updateAuthor(author);
        return "redirect:/authors";
    }




    @RequestMapping("/add")
    @ResponseBody
    public String add()
    {
        Random generator = new Random();

        Author author = new Author();
        author.setFirstName("Imię" + generator.nextInt());
        author.setLastName("Nazwisko" + generator.nextInt());


        authorDao.saveAuthor(author);
        return "Dodano:\n" + author.toString();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String update(@PathVariable Long id)
    {
        Random generator = new Random();
        Author author = authorDao.findById(id);
        author.setFirstName("Imię" + generator.nextInt());
        author.setLastName("Nazwisko" + generator.nextInt());
        authorDao.updateAuthor(author);

        return authorDao.findById(id).toString();
    }
}
