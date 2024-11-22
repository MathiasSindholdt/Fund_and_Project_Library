import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProposalsCsvWriter {
    
    public static void writeProposalCsv(String filepath, List<proposalProject> proposalList) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8"))) {
            writer.write('\ufeff'); // Write BOM to support UTF-8 encoding
            String[] header = {
                "Project Title", "Categories", "Description", "Purpose",
                "Owner", "Target Audience", "Budget", "TimeSpan From", "TimeSpan To", "Activities","Date Created"};

            writer.write(String.join(",", header));
            writer.newLine();

            for (proposalProject proposal : proposalList) {
                String[] proposalData = new String[11];
                proposalData[0] = proposal.getTitle();

                List<String> categories = proposal.getCategories();
                proposalData[1] = categories != null ? "\"" + String.join(", ", categories) + "\"" : "N/A";
                proposalData[2] = proposal.getDescription();
                proposalData[3] = proposal.getProjectPurpose();
                proposalData[4] = proposal.getProjectOwner();
                proposalData[5] = proposal.getProjectTargetAudience();
                proposalData[6] = String.valueOf(proposal.getProjectBudget());
                proposalData[7] = formatDateTime(proposal.getProjectTimeSpanFrom());
                proposalData[8] = formatDateTime(proposal.getProjectTimeSpanTo());
                proposalData[9] = proposal.getProjectActivities();
                proposalData[10] = formatDateTime(proposal.getDateCreated());

                String line = String.join(",", proposalData);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Proposal data written to file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
    
}
