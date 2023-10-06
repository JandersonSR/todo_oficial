package todo.validation.constraintvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import todo.validation.NotEmptyList;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
