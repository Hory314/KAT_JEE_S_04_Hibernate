package pl.coderslab.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MatureValidator implements ConstraintValidator<Mature, Integer>
{
    @Override
    public void initialize(Mature mature)
    {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext)
    {
        return LocalDate.now().getYear() - value >= 18;
    }
}
