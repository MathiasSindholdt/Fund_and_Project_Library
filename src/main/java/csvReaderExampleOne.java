import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class csvReaderExampleOne {

    public static void main(String[] args) {
        String inputFilePath = "Fondsoverblik(2).csv";

        ArrayList<String[]> data = readCsv(inputFilePath);

        String[] categoryNames = {
            "Attraktive EUD", "Trivsel", "Bæredygtighed, Demokrati",
            "STEM", "Iværksætteri", "Byggebranchen",
            "Fællesskab", "Teknologi, digitalisering og markedsføring",
            "Grøn omstilling", "Anlægsprojekter", "Piger i EUD/STEM",
            "Viden og uddannelse"
        };

        // Check if there is sufficient data
        if (data.size() < 2) {
            System.out.println("Insufficient data in the CSV file.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy 'kl.' HH:mm");

        List<fundClass> fundList = new ArrayList<>();

        // Process each subsequent row (fund)
        for (int row = 2; row < data.size(); row++) {
            String[] fundData = data.get(row);
            
            // Log the current row data for debugging
            System.out.println("Processing row " + (row + 1) + ": " + Arrays.toString(fundData));

            // Check if the row has a minimum number of columns
            if (fundData.length < 3) {
                System.out.println("Skipping row " + (row + 1) + ": insufficient columns.");
                continue;
            }

            // Extracting the relevant fund information
            String fundName = fundData[0];
            String fundWebsite = fundData[1]; 
            String fundDescription = fundData[2]; 
            String applicationDeadlineRaw = fundData.length > 3 ? fundData[3].trim() : "N/A";
            Object applicationDeadline = parseApplicationDeadline(applicationDeadlineRaw);
            Long[] budgetRange = fundData.length > 4 ? extractBudgetRange(fundData[4]) : new Long[]{null, null};
            Long budgetMin = budgetRange[0] != null ? budgetRange[0] : 0L;
            Long budgetMax = budgetRange[1] != null ? budgetRange[1] : 0L;
            String collaborationHistory = fundData.length > 6 ? fundData[6] : "N/A"; 
            
            fundClass fund = new fundClass();
            fund.setTitle(fundName);
            fund.setDescription(fundDescription);
            fund.setfundWebsite(fundWebsite);
            fund.setBudget(budgetMin, budgetMax);
            if (applicationDeadline instanceof LocalDate) {
                // Convert LocalDate to LocalDateTime at start of the day
                fund.setDeadlines(((LocalDate) applicationDeadline).atStartOfDay());
            } else if (applicationDeadline instanceof LocalDateTime) {
                fund.setDeadlines((LocalDateTime) applicationDeadline);
            } else if (applicationDeadline instanceof List) {
                // If it's a List, handle each element if needed
                List<?> dateList = (List<?>) applicationDeadline;
                if (!dateList.isEmpty() && dateList.get(0) instanceof LocalDate) {
                    // Using the first date in the list and converting to LocalDateTime
                    fund.setDeadlines(((LocalDate) dateList.get(0)).atStartOfDay());
                } else {
                    System.out.println("No valid date found in list for deadline.");
                }
            } else {
                System.out.println("Invalid date format for application deadline.");
            }

            ArrayList<String> includedCategories = new ArrayList<>();
            for (int categoryIndex = 0; categoryIndex < categoryNames.length; categoryIndex++) {
                if (fundData.length > 7 + categoryIndex && "x".equalsIgnoreCase(fundData[7 + categoryIndex].trim())) {
                    includedCategories.add(categoryNames[categoryIndex]);
                }
            }
            
            // Add a default category if none are marked
            if (includedCategories.isEmpty()) {
                includedCategories.add("NoCategory");
            }
            System.out.println(includedCategories);
            for (String category : includedCategories) {
                fund.setCategories(category);
            }
            fund.setCollaborationHistory(collaborationHistory);

            fundList.add(fund);

            System.out.println("The fund name is: " + fund.getTitle());           
            System.out.println("It has the following description: " + fund.getDescription());
            System.out.println("It was created at: " + fund.getDateCreated());
            System.out.println("It has the following categoreis: " + fund.getCategories());
            System.out.println("The deadlines for the fund is: " + fund.getDeadlines());
            System.out.println("Contact persons at the fund are: " + fund.getContacts());
            System.out.println("The fund has a budget of: " + fund.getBudgetSpan());
            System.out.println("We have previously collaborated on: " + fund.getCollaborationHistory());
            System.out.println("The fund website can be found at: " + fund.getFundWebsite());

            System.out.println("------------------------------------------------------");
        }
        FundCsvWriter.writeCsv("output.csv", fundList);

        
    }

    public static Object parseApplicationDeadline(String deadline) {
        int defaultDay = 01;
        int currentYear = LocalDate.now().getYear();

        if (deadline.toLowerCase().contains("running") || deadline.toLowerCase().contains("yearly")) {
            return LocalDate.of(1970, 1, 1);
        }
        System.out.println(deadline);

        deadline = deadline.trim();
        if (deadline.matches("^\\d{1,2}\\.\\s+\\w+")) {
            deadline = deadline.replaceFirst("\\.$", "").trim(); // Remove the period after the day
        }

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
            "d. MMMM yyyy",        // e.g., "24. oktober 2024"
            "d. MMMM 'og' d. MMMM",
            "d. MMMM '&' d. MMMM"
        };

        String[] dateTimePatterns = {
            "d. MMMM yyyy 'kl.' HH:mm",      // e.g., "24. oktober 2024 kl. 13:00"
            "d. MMMM yyyy 'klokken' HH",     // e.g., "24. oktober 2024 klokken 13"
            "d. MMMM yyyy 'kl.' HH",          // e.g., "24. oktober 2024 kl. 13"
            "d. MMMM yyyy HH:mm"
        };
        
        if (deadline.matches("^[a-zA-ZæøåÆØÅ]+$")) {  
            deadline = "1. " + deadline + " " + currentYear;
        }
    
        // Append the current year if date is missing it
        if (deadline.matches("\\d{1,2}\\.\\s+\\w+")) {
            deadline += " " + currentYear;
        }


        System.out.println("Attempting to parse deadline: " + deadline);

        if (deadline.matches("^[a-zA-ZæøåÆØÅ]+\\s+og\\s+[a-zA-ZæøåÆØÅ]+$")) {
            String[] monthParts = deadline.split("\\s+og\\s+");
            List<LocalDate> dates = new ArrayList<>();
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.forLanguageTag("da-DK"));

            for (String month : monthParts) {
                try {
                    // Parse the month and year, then add the default day afterward
                    YearMonth yearMonth = YearMonth.parse(month.trim() + " " + currentYear, monthFormatter);
                    // Convert YearMonth to LocalDate
                    LocalDate dateWithDefaultDay = yearMonth.atDay(defaultDay);  
                    dates.add(dateWithDefaultDay);
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing month-only date: " + month);
                    e.printStackTrace();
                    return null;
                }
            }
            return dates;
        }

        // If the deadline contains multiple dates separated by "og" or "&"
        if (deadline.contains(" og ") || deadline.contains("&")) {
            String separator = deadline.contains(" og ") ? " og " : "&";
            String[] dateParts = deadline.split(separator);

            System.out.println("Splitting deadline by '" + separator + "': " + Arrays.toString(dateParts));

            // Parse each date part individually and return them as a list
            List<LocalDate> dateList = new ArrayList<>();
            for (String datePart : dateParts) {
                String cleanDate = datePart.trim();

                // Try parsing with each date pattern
                for (String pattern : dateOnlyPatterns) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag("da-DK"));
                    try {
                        // Ensure the current year is added if missing
                        if (!cleanDate.contains(String.valueOf(currentYear))) {
                            cleanDate += " " + currentYear;
                        }

                        // Attempt to parse the date
                        LocalDate date = LocalDate.parse(cleanDate, formatter);
                        dateList.add(date);
                        break;  
                    } catch (DateTimeParseException e) {
                        System.out.println("Pattern failed for LocalDate: " + pattern + " for deadline: " + cleanDate);
                    }
                }
            }

            // Return the list of parsed dates if any were successfully parsed
            if (dateList.size() > 0) {
                return dateList;
            } else {
                System.out.println("Error parsing multiple dates: " + deadline);
                return null;
            }
        }

        // If only a single date is present, try parsing using defined patterns
        for (String pattern : dateOnlyPatterns) {
            deadline = deadline.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.forLanguageTag("da-DK"));
            try {
                return LocalDate.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Pattern failed for LocalDate: " + pattern + " for deadline: " + deadline);
            }
        }

        // If fail to parse with the patterns, try parsing as LocalDateTime if time is present
        for (String pattern : dateTimePatterns) {
            deadline = deadline.trim();
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

    public static String formatApplicationDeadline(Object deadline) {
        if (deadline == null) {
            return "N/A";
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (deadline instanceof LocalDate) {
            return ((LocalDate) deadline).format(dateFormatter);
        } else if (deadline instanceof LocalDateTime) {
            return ((LocalDateTime) deadline).format(dateTimeFormatter);
        } else if (deadline instanceof List<?>) {
            // Check if the object is a list and contains LocalDate instances
            List<?> dateList = (List<?>) deadline;

            // Iterate over the list and format each date
            StringBuilder formattedDates = new StringBuilder();
            for (int i = 0; i < dateList.size(); i++) {
                if (dateList.get(i) instanceof LocalDate) {
                    formattedDates.append(((LocalDate) dateList.get(i)).format(dateFormatter));
                    if (i < dateList.size() - 1) {
                        formattedDates.append(" og ");
                    }
                }
            }
            
            return formattedDates.toString();
        }

        return "Unknown format";
    }
    
    public static Long[] extractBudgetRange(String budget) {
        boolean isMillion = budget.toLowerCase().contains("mio");
    
        // Remove any non-digit or non-hyphen characters and split by '-'
        String[] parts = budget.replaceAll("[^\\d-]", "").split("-");
        Long budgetMin = null;
        Long budgetMax = null;
    
        try {
            if (parts.length == 2) {  // Handle ranges like "1-2 mio"
                budgetMin = Long.parseLong(parts[0].trim());
                budgetMax = Long.parseLong(parts[1].trim());
    
                if (isMillion) {
                    budgetMin *= 1_000_000;
                    budgetMax *= 1_000_000;
                }
            } else if (parts.length == 1) {
                budgetMin = Long.parseLong(parts[0].trim());
                if (isMillion) {
                    budgetMin *= 1_000_000;
                }
                budgetMax = budgetMin;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing budget: " + budget);
            return new Long[]{null, null};
        }
    
        return new Long[]{budgetMin, budgetMax};
    }

    public static ArrayList<String[]> readCsv(String inputFilePath) {
        ArrayList<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), StandardCharsets.UTF_8))) {
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
