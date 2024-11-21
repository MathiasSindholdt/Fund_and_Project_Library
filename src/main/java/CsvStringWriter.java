import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvStringWriter {
    public static void writeStringCSV(String filePath, ArrayList<String> categories) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String category : categories) {
                bw.write(category + ","); // Write each category separated by a comma
            }
            bw.newLine(); // Add a new line at the end
            System.out.println("CSV Write Successful.");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}