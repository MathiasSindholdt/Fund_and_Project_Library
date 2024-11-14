import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class proposalProject extends projectAbstract {
    //Normal parameter based constructor
    public proposalProject(
        String projectTitle,
        ArrayList<String> categories,
        String description,
        String purpose,
        String owner,
        String projectTargetAudience,
        Long projectBudget,
        LocalDateTime from,
        LocalDateTime to,
        String projectActivities
    ){
        this.setTitle(projectTitle);
        for (String category : categories) {
            this.setCategories(category);
        }
        this.setDescription(description);
        this.setProjectPurpose(purpose);
        this.setProjectOwner(owner);
        this.setProjectTargetAudience(projectTargetAudience);
        this.setProjectBudget(projectBudget);
        this.setTimeSpan(from, to);
        this.setProjectActivities(projectActivities);
    }

    //Overloaded constructor This enables the user of pure setters if only parts are needed to be changed
    //IMPORTANT! THIS REQUIRES MANUAL SETTING OF ALL PARAMETERS!
    public proposalProject(){
    }

    public static void main(String[] args) {
        // Convert array to ArrayList
        ArrayList<String> testCategories = new ArrayList<>(Arrays.asList("Byg", "Trivsel", "cat3"));
        
        // Instantiate proposalProject using the constructor
        proposalProject TestProposal = new proposalProject(
            "testProposal",
            testCategories,
            "Dette er en beskrivelse",
            "Dette er et formaal",
            "Dette er ejeren",
            "Her er hvem det skal goere godt for",
            80000000L,
            LocalDateTime.now().minusDays(1),
            LocalDateTime.now().plusDays(4),
            "Dette er noget vi skal have gjort"
        );

        System.out.println("Title: " + TestProposal.getTitle());
        System.out.println("Categories: ");
        for (String category : TestProposal.getCategories()) {
            System.out.println(category);
        }
        System.out.println("Description: " + TestProposal.getDescription());
        System.out.println("Purpose: " + TestProposal.getProjectPurpose());
        System.out.println("Owner: " + TestProposal.getProjectOwner());
        System.out.println("Target Audience: " + TestProposal.getProjectTargetAudience());
        System.out.println("Budget: " + TestProposal.getProjectBudget());
        System.out.println("TimeSpan (From): " + TestProposal.getProjectTimeSpanFrom());
        System.out.println("TimeSpan (To): " + TestProposal.getProjectTimeSpanTo());
        System.out.println("Activities: " + TestProposal.getProjectActivities());

        // Create a list of proposalProject instances
        List<proposalProject> proposals = new ArrayList<>();
        proposals.add(TestProposal);

        // Write the proposal list to a CSV file
        String filepath = "proposals.csv";
        ProposalsCsvWriter.writeProposalCsv(filepath, proposals);

        System.out.println("CSV file created at: " + filepath);

        List<proposalProject> readProposalProjects = ProposalCsvReader.readPropsalCsv(filepath);
        for (proposalProject prop : readProposalProjects) {
            System.out.println("Title: " + prop.getTitle());
            System.out.println("Categories: " + String.join(", ", prop.getCategories()));
            System.out.println("Description: " + prop.getDescription());
            System.out.println("Purpose: " + prop.getProjectPurpose());
            System.out.println("Owner: " + prop.getProjectOwner());
            System.out.println("Target Audience: " + prop.getProjectTargetAudience());
            System.out.println("Budget: " + prop.getProjectBudget());
            System.out.println("TimeSpan From: " + prop.getProjectTimeSpanFrom());
            System.out.println("TimeSpan To: " + prop.getProjectTimeSpanTo());
            System.out.println("Activities: " + prop.getProjectActivities());
            System.out.println();

        }
    }
}