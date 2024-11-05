import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class csvReaderExampleOne {

    public static void main(String[] args) {
        String filePath = "Fondsoverblik(2).csv";
        ArrayList<String[]> data = readCsv(filePath);

        // Check if there is sufficient data
        if (data.size() < 2) {
            System.out.println("Insufficient data in the CSV file.");
            return;
        }
        // Process each subsequent row (fund)
        for (int row = 2; row < data.size(); row++) {
            String[] fundData = data.get(row);
            
            // Ensure there's enough data in the row
            if (fundData.length < 19) {
                continue;
            }

            // Extracting the relevant fund information
            String fundName = fundData[0];
            String fundWebsite = fundData[1]; 
            String fundDescription = fundData[2]; 
            String applicationDeadline = fundData[3]; 
            String budgetSize = extractBudgetRange(fundData[4]);
            String collaborationHistory = fundData[6]; 

            // Output fund details
            System.out.println("Fund Name: " + fundName);
            System.out.println("Fund Website: " + fundWebsite);
            System.out.println("Description: " + fundDescription);
            System.out.println("Application Deadline: " + applicationDeadline);
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
                if ("x".equalsIgnoreCase(fundData[7 + categoryIndex].trim())) {
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


    public static String extractBudgetRange(String budget) {
        boolean isMillion = budget.toLowerCase().contains("mio");
        // Remove any non-numeric or non-dash characters, then split by the dash
        String[] parts = budget.replaceAll("[^\\d-]", "").split("-");
        
        // Check if there are two parts
        if (parts.length == 2) {
            
            return parts[0].trim() + " - " + parts[1].trim();
        } else if (parts.length == 1) {
            // Single budget amount
            return parts[0].trim();
        } else {
            // If there's no valid number, return a default message
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