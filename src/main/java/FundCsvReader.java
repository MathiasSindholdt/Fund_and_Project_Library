import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public static ArrayList<fundClass> readFundCsv(String filepath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<fundClass> funds = new ArrayList<>();
        long lineCounter = 0;
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();  // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                lineCounter++;
                // Skip the header row
                if (line.startsWith("Fund Name")) continue;

                // Process CSV line with regex to correctly handle commas inside quotes
                List<String> data = parseCsvLine(line);
                
                // If the line has fewer than 8 columns, pad the remaining columns with empty strings
                while (data.size() < 9) {
                    // Add empty strings for missing columns
                    data.add(""); 
                }

                // Now we have exactly 8 columns (even if some are empty)
                String fundName = data.get(0).trim();
                String website = data.get(1).trim();
                String description = data.get(2).trim();
                
                // Parsing the application deadline (format: "yyyy-MM-dd HH:mm")
                ArrayList<LocalDateTime> deadline = new ArrayList<>();
                for (String s : data.get(3).split(";")){
                try {
                    if (!s.equals("N/A") && !s.isEmpty()) {
                        deadline.add(LocalDateTime.parse(s.replace(" ", "T")));
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Skipping invalid date line: " + line);
                }
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

                // Parse contact information
                List<fundContactClass> contacts = parseContactInformation(data.get(8));

                LocalDateTime dateCreated;

                // replace | with , in the csv file after reading
                description = description.replace("|", ",");
                

                try{
                    dateCreated = LocalDateTime.parse(data.get(10).replace("\"", ""), formatter);
                } catch (Exception e) {
                    dateCreated = LocalDateTime.now().minusDays(lineCounter);
                }

                

                // Create a new fundClass object and set its properties
                fundClass fund = new fundClass();
                fund.setTitle(fundName);
                fund.setfundWebsite(website);
                fund.setDescription(description);
                for (LocalDateTime lDT : deadline){
                    fund.setDeadlines(lDT);
                }
                fund.setBudget(budgetMin, budgetMax);
                fund.setCollaborationHistory(collaborationHistory);
                categories.forEach(fund::setCategories);
                fund.setContacts(contacts);
                fund.setDateCreated(dateCreated);

    
                funds.add(fund);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("+++ Funds read successfull +++");
        for (fundClass fund : funds){
            System.out.println(fund.getTitle());
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

    private static List<fundContactClass> parseContactInformation(String contactColumn) {
        List<fundContactClass> contacts = new ArrayList<>();
    
        if (contactColumn == null || contactColumn.trim().isEmpty() || contactColumn.equals("N/A")) {
            return contacts;
        }
    
        // Split the contact column by " - " to separate different contacts
        String[] contactEntries = contactColumn.split("\\s+-\\s+");
        for (String entry : contactEntries) {
            // Split each contact entry by ", " to get individual fields
            String[] fields = entry.split(",\\s*");
            if (fields.length == 3) {
                // Create a new fundContactClass with parsed fields
                String name = fields[0].trim();
                String phone = fields[1].trim();
                String email = fields[2].trim();
                contacts.add(new fundContactClass(name, phone, email));
            }
        }
    
        return contacts;
    }
}
