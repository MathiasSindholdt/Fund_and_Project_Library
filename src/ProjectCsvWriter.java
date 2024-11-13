import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ProjectCsvWriter {
    public static void writeProjectCsv (String filepath, List<project> projects){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {

            String[] header = {
                "Project Title", "Categories", "Description", "Purpose", "Owner", 
                "Target Audience", "Budget", "TimeSpan From", "TimeSpan To", "Activities"};

            writer.write(String.join(",", header));
            writer.newLine();
            
            for (project myProject : projects) {
                String[] rowData = new String[10];
                // Specific project
                rowData[0] = myProject.getTitle();
                List<String> categories = myProject.getCategories();
                rowData[1] = categories != null ? String.join(", ", categories) : "N/A";
                rowData[2] = myProject.getDescription();
                rowData[3] = myProject.getProjectPurpose();
                rowData[4] = myProject.getProjectOwner();
                rowData[5] = myProject.getProjectTargetAudience();
                rowData[6] = String.valueOf(myProject.getProjectBudget());
                rowData[7] = formatDateTime(myProject.getProjectTimeSpanFrom());
                rowData[8] = formatDateTime(myProject.getProjectTimeSpanTo());
                rowData[9] = myProject.getProjectActivities();
                
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
