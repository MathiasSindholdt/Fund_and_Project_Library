import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CsvStringWriter {
    public static void writeStringCSV(String filePath, ArrayList<String> categories) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
            bw.write('\ufeff'); // Write BOM to support UTF-8 encoding

            for (String category : categories) {
                category = category.replace(",", "|"); // Replace commas with pipes
                bw.write(category + ","); // Write each category separated by a comma
            }
            bw.newLine(); // Add a new line at the end
            System.out.println("CSV Write Successful.");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}