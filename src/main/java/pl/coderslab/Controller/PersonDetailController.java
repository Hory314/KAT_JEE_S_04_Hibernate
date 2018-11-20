package pl.coderslab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dto.PersonDTO;

@Controller
@RequestMapping(path = "/persondetails", produces = "text/html;charset=UTF-8")
public class PersonDetailController
{

    @ModelAttribute("countries")
    public String[] countries()
    {
        return new String[]{"polska", "anglia", "niemcy"};
    }

    @ModelAttribute("skills")
    public String[] skills()
    {
        return new String[]{"C++", "PHP", "Java", "HTML"};
    }

    @ModelAttribute("hobbies")
    public String[] hobbies()
    {
        return new String[]{"film", "podróże", "sport"};
    }


    @GetMapping("/form")
    public String form(@ModelAttribute PersonDTO personDTO)
    {
        return "pdet-form";
    }

    @PostMapping("/form")
    @ResponseBody
    public String postForm(PersonDTO personDTO)
    {
        return personDTO.toString();
    }

}
