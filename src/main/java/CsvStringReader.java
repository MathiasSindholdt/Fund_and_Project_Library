import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CsvStringReader {
    public static ArrayList<String> readStringCsv(String filePath) {
        String tempFormattedString;
        ArrayList<String> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by commas
                String[] values = line.split(",");
                for (String value : values) {
                    value = value.replace("|", ",").replace("﻿", "").replace("™", "\n   "); // Replace pipes with commas
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