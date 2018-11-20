package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.PersonDao;
import pl.coderslab.Entity.Person;

import java.util.Random;

@Controller
@RequestMapping(path = "/person", produces = "text/html;charset=UTF-8")
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

    @GetMapping("/form")
    public String form(Model model)
    {
        model.addAttribute("newPerson", new Person());
        return "form";
    }

    @PostMapping("/form")
    public String postForm(Model model, @RequestParam String login, @RequestParam String email, @RequestParam String password)
    {
        Person person = new Person();
        person.setLogin(login);
        person.setEmail(email);
        person.setPassword(password);
        personDao.save(person);
        model.addAttribute("person_id", person.getId());
        return "form-success";
    }

    @GetMapping("/sform")
    public String form2(Model model)
    {
        model.addAttribute("newSpringPerson", new Person());
        return "sform";
    }

    @PostMapping("/sform")
    public String postForm2(Person person, Model model)
    {
        personDao.save(person);
        model.addAttribute("person_id", person.getId());
        return "form-success";
    }
}
