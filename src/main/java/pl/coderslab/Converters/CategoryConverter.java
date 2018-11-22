package pl.coderslab.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.Entity.Category;
import pl.coderslab.Repositories.CategoryRepository;

public class CategoryConverter implements Converter<String, Category>
{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(String s)
    {
        try
        {
            Long id = Long.parseLong(s);
            Category category = categoryRepository.findOne(id);
            return category;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
