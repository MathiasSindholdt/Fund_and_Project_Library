import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvStringReader {
    public static ArrayList<String> readStringCsv(String filePath) {
        ArrayList<String> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by commas
                String[] values = line.split(",");
                for (String value : values) {
                    categories.add(value.trim());
                }
            }
            System.out.println("CSV Read Successful." + categories);
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return categories;
    }
}