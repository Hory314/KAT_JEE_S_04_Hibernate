package pl.coderslab.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Publisher;

public class PublisherConverter implements Converter<String, Publisher>
{
    @Autowired
    private PublisherDao publisherDao;

    @Override
    public Publisher convert(String s)
    {
        Long id = Long.parseLong(s);
        Publisher publisher = publisherDao.findById(id);
        return publisher;
    }
}
