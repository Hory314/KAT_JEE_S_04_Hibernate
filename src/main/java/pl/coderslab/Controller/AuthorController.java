package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Entity.Author;

import java.util.Random;

@Controller
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return authorDao.findById(id).toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id)
    {
        Author authorToDel = authorDao.findById(id);
        authorDao.delete(authorToDel);
        return "Usunięto:\n" + authorToDel.toString();
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
