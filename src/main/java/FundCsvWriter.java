import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FundCsvWriter {

    public static void writeCsv(String filepath, List<fundClass> fundList) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8"))) {
            writer.write('\ufeff'); // Write BOM to support UTF-8 encoding

            // Writing the header row
            String[] header = {
                "Fund Name", "Website", "Description", "Application Deadline", 
                "Budget Min", "Budget Max", "Collaboration History", "Categories", "Contact Information","Date Created"
            };
            writer.write(String.join(",", header));
            writer.newLine();
            
            // Process each fund and create rows of data
             for (fundClass fund : fundList) {
            //     if (Objects.equals(fund.getFundWebsite(), "Website")){
            //         continue;
            //     }
                // Extract data from the fundClass object
                String[] fundData = new String[10];
                fundData[0] = escapeCsvField(fund.getTitle());  
                fundData[1] = escapeCsvField(fund.getFundWebsite());  
                fundData[2] = escapeCsvField(fund.getDescription());  
                fundData[3] = formatApplicationDeadline(fund.getDeadlines());
                fundData[4] = String.valueOf(fund.getBudgetMin()); 
                fundData[5] = String.valueOf(fund.getBudgetMax());  
                
                // Handle collaboration history and categories
                List<String> collaborationHistory = fund.getCollaborationHistory();
                fundData[6] = collaborationHistory != null ? escapeCsvField(String.join(", ", collaborationHistory)) : "N/A";
                
                // Handle categories as a comma-separated string
                List<String> categories = fund.getCategories();
                fundData[7] = categories != null ? "\"" + String.join(", ", categories) + "\"" : "N/A";
                
                List<fundContactClass> contacts = fund.getContacts();
                if (contacts != null && !contacts.isEmpty()) {
                    StringBuilder contactInfo = new StringBuilder();
                    for (fundContactClass contact : contacts) {
                        if (contactInfo.length() > 0) {
                            contactInfo.append(" - "); // Separator for multiple contacts
                        }

                        // Handle null/empty fields in contact information
                        String name = contact.getContactName() != null && !contact.getContactName().trim().isEmpty()
                                ? escapeCsvField(contact.getContactName().trim())
                                : "N/A";
                        String phone = contact.getContactPhoneNumber() != null && !contact.getContactPhoneNumber().trim().isEmpty()
                                ? escapeCsvField(contact.getContactPhoneNumber().trim())
                                : "N/A";
                        String email = contact.getContactEmail() != null && !contact.getContactEmail().trim().isEmpty()
                                ? escapeCsvField(contact.getContactEmail().trim())
                                : "N/A";

                        // Format the contact as "name, phone, email"
                        contactInfo.append(String.format("\"%s, %s, %s\"", name, phone, email));
                    }
                    fundData[8] = contactInfo.toString(); // Add to the CSV data row
                } else {
                    fundData[8] = "N/A"; // No contacts available
                }
                fundData[9] = formatDateTime(fund.getDateCreated());
                // Join fund data and write to file
                String line = String.join(",", fundData);
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Data written to file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    // Method to format the application deadline
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
            List<?> dateList = (List<?>) deadline;
            if (!dateList.isEmpty()) {
                String deadlines = new String();
                for (Object o : dateList){
                    if (o instanceof LocalDate) {
                        deadlines += ((LocalDate) o).format(dateFormatter);
                    } else if (o instanceof LocalDateTime) {
                        deadlines += ((LocalDateTime) o).format(dateTimeFormatter) + ";";
                    }
                }
                return deadlines;
            }
        }
        return "N/A";
    }

    // Method to escape fields that might contain commas or quotes
    private static String escapeCsvField(String field) {
        if (field == null) return "N/A";
        
        // Check if the field contains commas or quotes
        if (field.contains(",") || field.contains("\"")) {
            // Enclose the field in double quotes and escape internal quotes
            field = "\"" + field.replace("\"", "\"\"") + "\"";
        }
        
        return field;
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

}
