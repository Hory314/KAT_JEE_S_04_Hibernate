package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Dao.PersonDao;
import pl.coderslab.Entity.Person;

import java.util.Random;

@Controller
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    private PersonDao personDao;

    @RequestMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return personDao.findById(id).toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id)
    {
        Person perToDel = personDao.findById(id);
        personDao.delete(perToDel);
        return "Usunięto:\n" + perToDel.toString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add()
    {
        Random generator = new Random();

        Person person = new Person();
        person.setEmail("mail" + generator.nextInt());

        personDao.save(person);
        return "Dodano:\n" + person.toString();
    }

    @RequestMapping("/edit/{id}")
    @ResponseBody
    public String update(@PathVariable Long id)
    {
        Random generator = new Random();
        Person person = personDao.findById(id);
        person.setEmail("enamil" + generator.nextInt());
        personDao.update(person);

        return personDao.findById(id).toString();
    }

}
