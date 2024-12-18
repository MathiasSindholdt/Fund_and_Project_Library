import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectCsvReader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static ArrayList<project> readProjectCsv(String filepath) {
        ArrayList<project> projects = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        long lineCounter = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();  // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                lineCounter++;
                // Split CSV, handling quoted fields
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
                LocalDateTime dateCreated;
                Boolean isSensitive = Boolean.parseBoolean(values[11].replace("\"", ""));
                String assignedFund = null;


                // replacement of placeholders in the csv before reading
                description = description.replace("|", ",").replace("™", "\n");
                purpose = purpose.replace("|", ",");
                owner = owner.replace("|", ",");
                targetAudience = targetAudience.replace("|", ",");
                activities = activities.replace("|", ",");
                



                try{
                    dateCreated = LocalDateTime.parse(values[10].replace("\"", ""), formatter);
                } catch (Exception e) {
                    dateCreated = LocalDateTime.now().minusDays(lineCounter);
                }
                if (!(values.length < 13)){
                    assignedFund = values[12];
                }
                
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
                proj.setDateCreated(dateCreated);
                proj.setSensitive(isSensitive);
                proj.assignFundFromName(assignedFund);

                projects.add(proj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("+++ Projects read successfull +++");
        for (project project : projects){
            System.out.println(project.getTitle());
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
