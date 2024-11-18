import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FundCsvReader {

    // Regular expression to handle commas inside quoted fields
    private static final Pattern CSV_PATTERN = Pattern.compile(
            "(?:\"([^\"]*)\"|([^\",]*))(?:,|$)");

    // Method to read the fund data from a CSV file
    public static List<fundClass> readFundCsv(String filepath) {
        List<fundClass> funds = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Skip the header row
                if (line.startsWith("Fund Name")) continue;

                // Process CSV line with regex to correctly handle commas inside quotes
                List<String> data = parseCsvLine(line);
                
                // If the line has fewer than 8 columns, pad the remaining columns with empty strings
                while (data.size() < 8) {
                    // Add empty strings for missing columns
                    data.add(""); 
                }

                // Now we have exactly 8 columns (even if some are empty)
                String fundName = data.get(0).trim();
                String website = data.get(1).trim();
                String description = data.get(2).trim();
                
                // Parsing the application deadline (format: "yyyy-MM-dd HH:mm")
                LocalDateTime deadline = null;
                try {
                    if (!data.get(3).equals("N/A") && !data.get(3).isEmpty()) {
                        deadline = LocalDateTime.parse(data.get(3).replace(" ", "T"));
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Skipping invalid date line: " + line);
                }

                // Handle Budget Min and Max (if not empty, parse them as longs)
                long budgetMin = 0;
                long budgetMax = 0;
                try {
                    if (!data.get(4).trim().isEmpty()) {
                        budgetMin = Long.parseLong(data.get(4).trim());
                    }
                    if (!data.get(5).trim().isEmpty()) {
                        budgetMax = Long.parseLong(data.get(5).trim());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid budget range: " + line);
                }

                // Parsing collaboration history (semicolon-separated list)
                String collaborationHistory = data.get(6).replace("\"", "").trim();
                
                // Parsing categories (comma-separated list)
                List<String> categories = Arrays.asList(data.get(7).replace("\"", "").split(",\\s*"));

                // Create a new fundClass object and set its properties
                fundClass fund = new fundClass();
                fund.setTitle(fundName);
                fund.setfundWebsite(website);
                fund.setDescription(description);
                fund.setDeadlines(deadline);
                fund.setBudget(budgetMin, budgetMax);
                fund.setCollaborationHistory(collaborationHistory);
                categories.forEach(fund::setCategories);
    
                funds.add(fund);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return funds;
    }

    // Method to handle CSV parsing with commas inside quotes
    private static List<String> parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        Matcher matcher = CSV_PATTERN.matcher(line);
        
        while (matcher.find()) {
            String quotedField = matcher.group(1);
            String unquotedField = matcher.group(2);
            
            // If a quoted field is found, use that, otherwise use the unquoted field
            result.add(quotedField != null ? quotedField : unquotedField);
        }
        
        return result;
    }
}
