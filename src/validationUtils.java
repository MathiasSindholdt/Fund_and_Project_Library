
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class validationUtils {
    public static final Pattern VALID_CHARACTERS = Pattern.compile("^[a-zA-Z0-9 .,;:\"'?@]*$");
    public static final Pattern VALID_URL = Pattern.compile("^[a-zA-Z0-9 .;:\"'!?]*$");
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

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
