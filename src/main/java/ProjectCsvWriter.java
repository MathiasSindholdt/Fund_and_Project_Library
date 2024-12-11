import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ProjectCsvWriter {
    public static void writeProjectCsv (String filepath, List<project> projects){
        String tempFormattedString;
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8"))) {
            writer.write('\ufeff'); // Write BOM to support UTF-8 encoding

            String[] header = {
                "Project Title", "Categories", "Description", "Purpose", "Owner", 
                "Target Audience", "Budget", "TimeSpan From", "TimeSpan To", "Activities","Date Created","Assigned Fund"};

            writer.write(String.join(",", header));
            writer.newLine();
            
            for (project myProject : projects) {

                String[] rowData = new String[13];
                // Specific project
                rowData[0] = myProject.getTitle();
                List<String> categories = myProject.getCategories();
                rowData[1] = categories != null ? "\"" + String.join(", ", categories) + "\"" : "N/A";
                tempFormattedString = myProject.getDescription().replace(",", "|").replace("\n", "™");
                rowData[2] = tempFormattedString;//myProject.getDescription();
                tempFormattedString = myProject.getProjectPurpose().replace(",", "|");
                rowData[3] = tempFormattedString;//myProject.getProjectPurpose();
                tempFormattedString = myProject.getProjectOwner().replace(",", "|");
                rowData[4] = tempFormattedString;//myProject.getProjectOwner();
                tempFormattedString = myProject.getProjectTargetAudience().replace(",", "|");
                rowData[5] = tempFormattedString; //myProject.getProjectTargetAudience();
                rowData[6] = String.valueOf(myProject.getProjectBudget());
                rowData[7] = formatDateTime(myProject.getProjectTimeSpanFrom());
                rowData[8] = formatDateTime(myProject.getProjectTimeSpanTo());
                tempFormattedString = myProject.getProjectActivities().replace(",", "|");
                rowData[9] = tempFormattedString;//myProject.getProjectActivities();
                rowData[10] = formatDateTime(myProject.getDateCreated());
                rowData[11] = String.valueOf(myProject.getSensitive());
                if (myProject.getFund() != null){
                    rowData[12] = myProject.getFund().getTitle();
                } else {
                    rowData[12] = "Ingen Beviling";
                }
                
                
                writer.write(String.join(",", rowData));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    private static String formatDeadlines(List<LocalDateTime> deadlines) {
        if (deadlines == null || deadlines.isEmpty()) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<String> formattedDates = new ArrayList<>();
        for (LocalDateTime deadline : deadlines) {
            formattedDates.add(deadline.format(formatter));
        }
        return String.join(", ", formattedDates);
    }
}
