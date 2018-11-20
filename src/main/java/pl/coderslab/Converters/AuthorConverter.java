package pl.coderslab.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.Dao.AuthorDao;
import pl.coderslab.Dao.PublisherDao;
import pl.coderslab.Entity.Author;
import pl.coderslab.Entity.Publisher;

import java.util.List;

public class AuthorConverter implements Converter<String, Author>
{
    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author convert(String s)
    {
        Long id = Long.parseLong(s);
        Author authors = authorDao.findById(id);
        return authors;
    }
}
