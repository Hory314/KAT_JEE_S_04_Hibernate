package pl.coderslab.Controller;

import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.PublisherDao;

import pl.coderslab.Entity.Publisher;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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


    @GetMapping("/create")
    public String publishers(Model model)
    {
        model.addAttribute("newPublisher", new Publisher());
        return "/publishers/create";
    }

    @PostMapping("/create")
    public String savePublishers(@ModelAttribute("newPublisher") @Valid Publisher publisher, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "/publishers/create";
        }

        publisherDao.savePublisher(publisher);
        return "redirect:/publishers";
    }


    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("newPublisher", publisherDao.findById(id));
        return "/publishers/create";
    }

    @PostMapping("/edit/{id}")
    public String postUpdate(@ModelAttribute("newPublisher") @Valid Publisher publisher, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "/publishers/create";
        }
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

}
