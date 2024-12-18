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

    public static ArrayList<proposalProject> readProposalCsv(String filepath) {
        ArrayList<proposalProject> proposals = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        long lineCounter = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8))) {

            String headerLine = br.readLine();  // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                lineCounter++;
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
                LocalDateTime dateCreated;

                // replacement of | with , in the csv file after reading
                description = description.replace("|", ",").replace("™", "\n");
                purpose = purpose.replace("|", ",").replace("™", "\n");
                owner = owner.replace("|", ",").replace("™", "\n");
                targetAudience = targetAudience.replace("|", ",").replace("™", "\n");
                activities = activities.replace("|", ",").replace("™", "\n");
                

                try{
                    dateCreated = LocalDateTime.parse(values[10].replace("\"", ""), formatter);
                } catch (Exception e) {
                    dateCreated = LocalDateTime.now().minusDays(lineCounter);
                }
                

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
                prop.setDateCreated(dateCreated);

                proposals.add(prop);
            }
        } catch (IOException e) {
            System.out.println("!!! Proposal read unsuccessfull !!!");
            e.printStackTrace();
        }
        System.out.println("+++ Proposal read successfull +++");
        for (proposalProject proposal : proposals){
            System.out.println(proposal.getTitle());
        }
        return proposals;
    }
}
