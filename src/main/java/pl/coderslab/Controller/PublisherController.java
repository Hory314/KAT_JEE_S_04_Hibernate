package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Publisher;

import java.util.Random;

@Controller
@RequestMapping("/publishers")
public class PublisherController
{
    @Autowired
    private PublisherDao publisherDao;

    @RequestMapping("/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id)
    {
        return publisherDao.findById(id).toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id)
    {
        Publisher PublisherToDel = publisherDao.findById(id);
        publisherDao.delete(PublisherToDel);
        return "Usunięto:\n" + PublisherToDel.toString();
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

    @RequestMapping("/edit/{id}")
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
