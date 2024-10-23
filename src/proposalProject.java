import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class proposalProject extends projectAbstract {

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
    }
}