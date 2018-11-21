package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.PublisherDao;

import pl.coderslab.Entity.Publisher;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/publishers")
public class PublisherController
{
    @Autowired
    private PublisherDao publisherDao;

    @RequestMapping
    public String showPublishers(Model model)
    {
        model.addAttribute("publishers", publisherDao.findAll());
        return "/publishers/all";
    }


    @GetMapping("/create") // dzien2/czesc2
    public String publishers(Model model)
    {
        model.addAttribute("newPublisher", new Publisher());
        return "/publishers/create";
    }

    @PostMapping("/create")
    public String savePublishers(@ModelAttribute Publisher publishers)
    {
        publisherDao.savePublisher(publishers);
        return "redirect:/publishers";
    }


    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("newPublisher", publisherDao.findById(id));
        return "/publishers/create";
    }

    @PostMapping("/edit/{id}")
    public String postUpdate(@ModelAttribute Publisher publisher)
    {
        publisherDao.updatePublisher(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping("/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model)
    {
        model.addAttribute("id", id);
        return "/publishers/del-confirm";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "redirect:/publishers";
    }


    @RequestMapping("/a/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return publisherDao.findById(id).toString();
    }


    @RequestMapping("/add")
    @ResponseBody
    public String add()
    {
        Random generator = new Random();

        Publisher publisher = new Publisher();
        publisher.setName("Wydawnictwo" + generator.nextInt());

        publisherDao.savePublisher(publisher);
        return "Dodano:\n" + publisher.toString();
    }

    @RequestMapping("/a/edit/{id}")
    @ResponseBody
    public String update(@PathVariable Long id)
    {
        Random generator = new Random();
        Publisher publisher = publisherDao.findById(id);
        publisher.setName("Wydawnictwo" + generator.nextInt());
        publisherDao.updatePublisher(publisher);

        return publisherDao.findById(id).toString();
    }
}
