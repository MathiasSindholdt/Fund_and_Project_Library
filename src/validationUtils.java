import java.util.regex.Pattern;

public class validationUtils {
    public static final Pattern ValidCharacters = Pattern.compile("^[a-zA-Z0-9 .,;:\"'?]*$");

    public static class WrongDataInputException extends IllegalArgumentException {
        public WrongDataInputException(String message) {
            super(message);
        }
    }

    public static boolean isValidInput(String input){
        return ValidCharacters.matcher(input).matches();

    }

    public static void validateInput(String input, String fieldName) {
        if (!isValidInput(input)) {
            throw new WrongDataInputException(fieldName + " contains invalid characters");
        }
    } 
}
