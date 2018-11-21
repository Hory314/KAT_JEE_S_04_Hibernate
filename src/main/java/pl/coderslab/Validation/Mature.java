package pl.coderslab.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MatureValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mature
{
    String message() default "{pl.coderslab.springhibernate.validation.Mature.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
