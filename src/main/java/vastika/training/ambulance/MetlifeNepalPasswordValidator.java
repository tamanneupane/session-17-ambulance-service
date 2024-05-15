package vastika.training.ambulance;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MetlifeNepalPasswordValidator implements ConstraintValidator<MetlifeNepalPasswordConstraint, String> {
    private static final String PASSWORD_LENGTH_PATTERN = ".{8,40}";
    private static final String ALPHABETICAL_PATTERN = ".*[a-zA-Z]+.*";
    private static final String NUMERIC_PATTERN = ".*\\d+.*";



    private static final Pattern lengthPattern = Pattern.compile(PASSWORD_LENGTH_PATTERN);
    private static final Pattern alphabeticalPattern = Pattern.compile(ALPHABETICAL_PATTERN);
    private static final Pattern numericPattern = Pattern.compile(NUMERIC_PATTERN);

    @Override
    public void initialize(MetlifeNepalPasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (password == null || password.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorConstants.PASSWORD_EMPTY_OR_NULL).addConstraintViolation();
            return false; // Return false if password is null or empty
        }

        if (!lengthPattern.matcher(password).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorConstants.PASSWORD_LENGTH).addConstraintViolation();
            isValid = false;
        }

        if (!alphabeticalPattern.matcher(password).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorConstants.PASSWORD_ONE_ALPHABETIC_CHAR).addConstraintViolation();
            isValid = false;
        }

        if (!numericPattern.matcher(password).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorConstants.PASSWORD_ONE_NUMERIC_CHAR).addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
