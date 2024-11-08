import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class csvReaderExampleOne {

    public static void main(String[] args) {
        String filePath = "Fondsoverblik(2).csv";
        ArrayList<String[]> data = readCsv(filePath);

        // Check if there is sufficient data
        if (data.size() < 2) {
            System.out.println("Insufficient data in the CSV file.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy 'kl.' HH:mm");

        // Process each subsequent row (fund)
        for (int row = 2; row < data.size(); row++) {
            String[] fundData = data.get(row);
            
            // Log the current row data for debugging
            System.out.println("Processing row " + (row + 1) + ": " + Arrays.toString(fundData));

            // Check if the row has a minimum number of columns
            if (fundData.length < 3) { // Adjusted minimum length check to 3
                System.out.println("Skipping row " + (row + 1) + ": insufficient columns.");
                continue;
            }

            // Extracting the relevant fund information
            String fundName = fundData[0];
            String fundWebsite = fundData[1]; 
            String fundDescription = fundData[2]; 
            String applicationDeadlineRaw = fundData.length > 3 ? fundData[3].trim() : "N/A";
            Temporal applicationDeadline = parseApplicationDeadline(applicationDeadlineRaw);
            String budgetSize = fundData.length > 4 ? extractBudgetRange(fundData[4]) : "N/A";
            String collaborationHistory = fundData.length > 6 ? fundData[6] : "N/A"; 

            // Output fund details
            System.out.println("Fund Name: " + fundName);
            System.out.println("Fund Website: " + fundWebsite);
            System.out.println("Description: " + fundDescription);
            //System.out.println("Application Deadline: " + (applicationDeadline != null ? applicationDeadline : "N/A"));
            System.out.println("Application Deadline: " + formatApplicationDeadline(applicationDeadline));
            System.out.println("Budget Size: " + budgetSize);
            System.out.println("Collaboration History: " + collaborationHistory);

            // Define the category names based on columns 7 to 18
            String[] categoryNames = {
                "Attraktive EUD", "Trivsel", "Bæredygtighed, Demokrati",
                "STEM", "Iværksætteri", "Byggebranchen",
                "Fællesskab", "Teknologi, digitalisering og markedsføring",
                "Grøn omstilling", "Anlægsprojekter", "Piger i EUD/STEM",
                "Viden og uddannelse"
            };

            boolean hasIncludedCategory = false;
            ArrayList<String> includedCategories = new ArrayList<>();
           
            for (int categoryIndex = 0; categoryIndex < categoryNames.length; categoryIndex++) {
                if (fundData.length > 7 + categoryIndex && "x".equalsIgnoreCase(fundData[7 + categoryIndex].trim())) {
                    includedCategories.add(categoryNames[categoryIndex]);
                    hasIncludedCategory = true;
                }
            }

            // Print included categories or "NoCategory"
            if (hasIncludedCategory) {
                System.out.println("\nIncluded Categories:");
                for (String category : includedCategories) {
                    System.out.println(category);
                }
            } else {
                System.out.println("NoCategory");
            }

            System.out.println("------------------------------------------------------");
        }
    }

    public static Temporal parseApplicationDeadline(String deadline) {

        int currentYear = LocalDate.now().getYear();

        if (deadline.toLowerCase().contains("running")) {
        return LocalDate.of(1970, 1, 1);
        }
        System.out.println(deadline);

        // Define patterns for date-only and date-time formats
        String[] dateOnlyPatterns = {
            "d. MMMM yyyy",          // e.g., "24. oktober 2024"
            "d. MMMM",               // e.g., "24. oktober" (assumes current year)
            "d/MMMM/yyyy",           // e.g., "01/oktober/2024"
            "dd. MMMM yyyy",         // e.g., "01. oktober 2024"
            "d-M-yyyy",              // e.g., "24-10-2024"
            "d/M/yyyy",              // e.g., "24/10/2024"
            "d MMM yyyy",            // e.g., "24 okt 2024"
            "dd-MM-yyyy",            // e.g., "24-10-2024"
            "d. MMMM yyyy"           // e.g., "24. oktober 2024"
        };

        String[] dateTimePatterns = {
            "d. MMMM yyyy 'kl.' HH:mm",      // e.g., "24. oktober 2024 kl. 13:00"
            "d. MMMM yyyy 'klokken' HH",     // e.g., "24. oktober 2024 klokken 13"
            "d. MMMM yyyy 'kl.' HH"          // e.g., "24. oktober 2024 kl. 13"
        };

        // Append the current year if date is missing it
        if (deadline.matches("\\d{1,2}\\.\\s+\\w+")) {
        deadline += " " + currentYear;
        }

        System.out.println("Attempting to parse deadline: " + deadline);

        // Try parsing as LocalDate
        for (String pattern : dateOnlyPatterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag("da-DK"));
            try {
                return LocalDate.parse(deadline, formatter);
            }catch (DateTimeParseException e) {
                System.out.println("Pattern failed for LocalDate: " + pattern + " for deadline: " + deadline);
            }
        }

        // Try parsing as LocalDateTime if the date has a time component
        for (String pattern : dateTimePatterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag("da-DK"));
            try {
                return LocalDateTime.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Pattern failed for LocalDateTime: " + pattern + " for deadline: " + deadline);
            }
        }

        System.out.println("Error parsing application deadline: " + deadline);
        return null;
    }

public static String formatApplicationDeadline(Temporal deadline) {
    if (deadline == null) {
        return "N/A";
    }
    
    DateTimeFormatter outputFormatter;
    if (deadline instanceof LocalDateTime) {
        outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return ((LocalDateTime) deadline).format(outputFormatter);
    } else if (deadline instanceof LocalDate) {
        outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ((LocalDate) deadline).format(outputFormatter);
    }
    
    return "N/A";
}
    
    public static String extractBudgetRange(String budget) {
        boolean isMillion = budget.toLowerCase().contains("mio");
        
        String[] parts = budget.replaceAll("[^\\d-]", "").split("-");
        
        if (parts.length == 2) { 
            String start = parts[0].trim();
            String end = parts[1].trim();
            
            if (isMillion){
                end += "000000";
            }

            return start + " - " + end;
        } else if (parts.length == 1) {
            String amount = parts[0].trim();
            if (isMillion) {
                amount += "000000";
            }
            return amount;
        } else {

            return "N/A";
        }
    }

    public static ArrayList<String[]> readCsv(String filePath) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                ArrayList<String> cells = new ArrayList<>();
                StringBuilder cell = new StringBuilder();
                boolean inQuotes = false;

                for (int i = 0; i < line.length(); i++) {
                    char currentChar = line.charAt(i);

                    // Toggle inQuotes when encountering a double quote
                    if (currentChar == '\"') {
                        inQuotes = !inQuotes;
                    } else if (currentChar == ',' && !inQuotes) {
                        // If a comma is encountered outside quotes, end the cell
                        cells.add(cell.toString().trim());
                        cell.setLength(0); // Clear the cell buffer
                    } else {
                        // Add characters to the cell
                        cell.append(currentChar);
                    }
                }

                // Add the last cell in the line
                cells.add(cell.toString().trim());

                // Add the row to the list
                rows.add(cells.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
