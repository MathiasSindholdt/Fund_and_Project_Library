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

public class ProposalCsvReader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static List<proposalProject> readPropsalCsv(String filepath) {
        List<proposalProject> proposals = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {

            String headerLine = br.readLine();  // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
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

                proposalProject prop = new proposalProject();
                prop.setTitle(title);
                categories.forEach(prop::setCategories);
                prop.setDescription(description);
                prop.setProjectPurpose(purpose);
                prop.setProjectOwner(owner);
                prop.setProjectTargetAudience(targetAudience);
                prop.setProjectBudget(budget);
                prop.setTimeSpan(timeSpanFrom, timeSpanTo);
                prop.setProjectActivities(activities);

                proposals.add(prop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proposals;
    }
}
