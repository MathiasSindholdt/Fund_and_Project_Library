import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectCsvReader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static List<project> readProjectCsv(String filepath) {
        List<project> projects = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String headerLine = reader.readLine();  // Skip header line
            String line;

            while ((line = reader.readLine()) != null) {
                // Use regex to split CSV, handling quoted fields
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String title = values[0].replace("\"", "");
                List<String> categories = Arrays.asList(values[1].replace("\"", "").split(", "));
                String description = values[2].replace("\"", "");
                String purpose = values[3].replace("\"", "");
                String owner = values[4].replace("\"", "");
                String targetAudience = values[5].replace("\"", "");
                Long budget = Long.parseLong(values[6].replace("\"", ""));
                LocalDateTime timeSpanFrom = LocalDateTime.parse(values[7].replace("\"", ""), formatter);
                LocalDateTime timeSpanTo = LocalDateTime.parse(values[8].replace("\"", ""), formatter);
                String activities = values[9].replace("\"", "");

                project proj = new project();
                proj.setTitle(title);
                categories.forEach(proj::setCategories);
                proj.setDescription(description);
                proj.setProjectPurpose(purpose);
                proj.setProjectOwner(owner);
                proj.setProjectTargetAudience(targetAudience);
                proj.setProjectBudget(budget);
                proj.setTimeSpan(timeSpanFrom, timeSpanTo);
                proj.setProjectActivities(activities);

                projects.add(proj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projects;
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr.equals("N/A")) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DATE_FORMATTER);
    }
}
