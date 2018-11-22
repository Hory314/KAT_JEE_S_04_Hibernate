package pl.coderslab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Entity.Book;
import pl.coderslab.Validation.BookValidationGroup;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping(path = "/validation", produces = "text/html;charset=UTF-8")
public class ValidationController
{
    @Autowired
    Validator validator;

    @GetMapping("/test")
    public String testValidation(Model model)
    {
        Book book = new Book();
        book.setRating(0.1);
        book.setDescription("asf");
        book.setTitle("a");
        book.setPages(1);

        Set<ConstraintViolation<Book>> errors = validator.validate(book, BookValidationGroup.class);

        if (!errors.isEmpty())
        {
//            System.out.println("Wystąpiły błędy");
//
//            for (ConstraintViolation<Book> violation : errors)
//            {
//                Path path = violation.getPropertyPath();
//                String message = violation.getMessage();
//                Object invalidValue = violation.getInvalidValue();
//                System.out.printf("Dla %s niepoprawana warartość %s, teść błędu: '%s'\n", path, invalidValue, message);
//            }
            model.addAttribute("validationErrors", errors);
        }
        else
        {
            model.addAttribute("validationErrors", null);
            System.out.println("Ok");

        }
        return "/validation/test";
    }
}
