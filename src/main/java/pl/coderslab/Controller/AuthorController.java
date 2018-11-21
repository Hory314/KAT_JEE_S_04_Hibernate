package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Entity.Author;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthorController
{
    @Autowired
    private AuthorDao authorDao;

    // SHOW ALL
    @RequestMapping("/authors")
    public String showAuthors(Model model)
    {
        model.addAttribute("authors", authorDao.findAll());
        return "/authors/all";
    }

    // ADD
    @GetMapping("/authors/create")
    public String authors(HttpServletRequest request)
    {
        request.setAttribute("newAuthor", new Author());
        return "/authors/create";
    }

    @PostMapping("/authors/create")
    public String saveAuthor(@ModelAttribute("newAuthor") @Valid Author author, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/authors/create";
        }

        authorDao.saveAuthor(author);
        return "redirect:/authors";
    }

    //DELETE
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

    // EDIT
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
}
