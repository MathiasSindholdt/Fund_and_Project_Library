
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class validationUtils {
    public static final Pattern VALID_CHARACTERS = Pattern.compile("^[a-zA-Z0-9 .,;:\"'?@]*$");
    public static final Pattern VALID_URL = Pattern.compile("^[a-zA-Z0-9 .;:\"'!?]*$");
    public static final Pattern VALID_DESCRIPTION = Pattern.compile("^[a-zA-Z0-9 .,;:\"'?\\t()]*$");
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final int MIN_LENGTH = 1;
    public static final int LOWER_MAX_LENGTH = 200;
    public static final int UPPER_MAX_LENGTH = 3000;

    public static final Pattern NUMERIC_CHARACTERS = Pattern.compile("^[0-9]+$");

    public static class WrongDataInputException extends IllegalArgumentException {
        public WrongDataInputException(String message) {
            super(message);
        }
    }

    public static boolean isValidInput(String input){
        return VALID_CHARACTERS.matcher(input).matches();
    }

    public static boolean isValidUrl(String input){
        return VALID_URL.matcher(input).matches();
    }

    public static boolean isNumericInput(String input){
        return NUMERIC_CHARACTERS.matcher(input).matches();
    }

    public static boolean isValidDescription(String input){
        return VALID_DESCRIPTION.matcher(input).matches();
    }

    public static boolean isWithinLowerCharLimit(String input){
        return input != null && input.length() >= MIN_LENGTH && input.length() <= LOWER_MAX_LENGTH;
    }

    public static boolean isWithinUpperCharLimit(String input){
        return input != null && input.length() >= MIN_LENGTH && input.length() <= UPPER_MAX_LENGTH;
    }

    public static void validateInput(String input, String fieldName) {
        if (!isValidInput(input)) {
            throw new WrongDataInputException(fieldName + " contains invalid characters");
        }
    } 

    public static void validateURL(String input, String fieldName) {
        if (!isValidUrl(input)) {
            throw new WrongDataInputException(fieldName + " contains invalid characters");
        }
    } 

}
