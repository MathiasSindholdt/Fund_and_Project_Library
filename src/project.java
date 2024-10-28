import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class project extends projectAbstract {
    private ArrayList<fundClass> closestDeadlineFunds = new ArrayList<fundClass>();
    public project(
        String projectTitle,
        ArrayList<String> categories,
        String description,
        String purpose,
        String owner,
        String projectTargetAudience,
        Long projectBudget,
        LocalDateTime from,
        LocalDateTime to,
        String projectActivities,
        ArrayList<fundClass> fundList,
        boolean onlyOneIsNeeded
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
        this.setClosestDeadlineFunds(fundList, onlyOneIsNeeded);
    }

    //Overloaded constructor This enables the user of pure setters if only parts are needed to be changed
    //IMPORTANT! THIS REQUIRES MANUAL SETTING OF ALL PARAMETERS!
    public project(){
    }

    public ArrayList<fundClass> getClosestDeadlineFunds(){
        return closestDeadlineFunds;
    }


    public void setClosestDeadlineFunds(ArrayList<fundClass> fundList, boolean onlyOneIsNeeded){
        sortingFundDeadlines sorter = new sortingFundDeadlines();
        closestDeadlineFunds =  sorter.sortFundsForCloesestDeadlines(fundList, this, onlyOneIsNeeded);
    }

    public static void main(String[] args) {

        ArrayList<fundClass> fundList = new ArrayList<fundClass>();
        String[] cat1 = {"Byg", "Trivsel", "Undervisning"};
        String[] cat2 = {"Byg", "Random", "Undervisning"};
        String[] cat3 = {"Byg", "Trivsel", "Undervisning"};
        String[] contact1 = {"Anders", "Lucas"};
        String[] contact2 = {"Lund", "Walther"};
        String[] collab1 = {"P1", "P2"};
        String[] collab2 = {"P2", "P3"};
        LocalDateTime[] fund1Deadline = {LocalDateTime.parse("2024-10-04T12:00:00"), LocalDateTime.parse("2024-12-05T12:00:00")};
        LocalDateTime[] fund2Deadline = {LocalDateTime.parse("2024-11-05T12:00:00"), LocalDateTime.parse("2024-09-05T12:00:00")};
        fundClass fund0 = new fundClass();
        fund0.setTitle("fund0");
        fund0.setDescription("This is fund0");
        fund0.setCommonBudget(100000);
        for (String category : cat1) {
            fund0.setCategories(category);

        }
        for (String contact : contact1) {
            fund0.setContacts(contact);
        }
        for (String collab : collab1) {
            fund0.setContacts(collab);
        }
        fund0.setDeadlines(LocalDateTime.parse("2024-10-04T12:00:00"));

        fundClass fund1 = new fundClass();
        fund1.setTitle("fund1");
        fund1.setDescription("This is fund1");
        fund1.setCommonBudget(1000);
        for (String category : cat2) {
            fund1.setCategories(category);
        }
        for (String contact : contact2) {
            fund1.setContacts(contact);
        }
        for (String collab : collab2) {
            fund1.setCollaborationHistory(collab);
        }
        for (LocalDateTime deadline : fund1Deadline) {
            fund1.setDeadlines(deadline);
        }

        fundClass fund2 = new fundClass();
        fund2.setTitle("fund2");
        fund2.setDescription("This is fund2");
        fund2.setCommonBudget(1000000);
        for (String category : cat3) {
            fund2.setCategories(category);
        }
        for (String contact : contact2) {
            fund2.setContacts(contact);
        }
        for (String collab : collab2) {
            fund2.setCollaborationHistory(collab);
        }
        for (LocalDateTime deadline : fund2Deadline) {
            fund2.setDeadlines(deadline);
        }
        fundList.add(fund0);
        fundList.add(fund1);
        fundList.add(fund2);

        project myProject1 = new project(
            "ProjectTitle1",
            new ArrayList<>(Arrays.asList(cat1)),
            "This is a description",
            "Purpose1",
            "Anders",
            "TargetAudience1",
            10000L,
            LocalDateTime.parse("2024-10-11T12:00:00"),
            LocalDateTime.parse("2024-12-12T12:00:00"),
            "Activities1",
            fundList,
            true
        );
        project myProject2 = new project();
        myProject2.setTitle("ProjectTitle2");
        for (String category : cat2){
            myProject2.setCategories(category);
        }
        myProject2.setDescription("This is a description too");
        myProject2.setProjectPurpose("Purpose2");
        myProject2.setProjectOwner("Lucas");
        myProject2.setProjectTargetAudience("TargetAudience2");
        myProject2.setProjectBudget(10055000);
        myProject2.setTimeSpan(LocalDateTime.parse("2024-10-17T12:00:00"), LocalDateTime.parse("2024-12-24T12:00:00"));
        myProject2.setProjectActivities("Activities2");
        myProject2.setClosestDeadlineFunds(fundList, false);

        System.out.println("TYPE: " + myProject1.getClass());
        System.out.println("Here is the myProject1: " + myProject1.getTitle());
        System.out.println("It has the following description: " + myProject1.getDescription());
        System.out.println("It was created at: " + myProject1.getDateCreated());
        System.out.println("It has the following categoreis: " + myProject1.getCategories());
        System.out.println("The main idea is: " + myProject1.getProjectPurpose());
        System.out.println("The myProject1 Owner is: " + myProject1.getProjectOwner());
        System.out.println("The main audience is: " + myProject1.getProjectTargetAudience());
        System.out.println("The following should be completed: " + myProject1.getProjectActivities());

        System.out.println("TYPE: " + myProject2.getClass());
        System.out.println("Here is the myProject2: " + myProject2.getTitle());
        System.out.println("It has the following description: " + myProject2.getDescription());
        System.out.println("It was created at: " + myProject2.getDateCreated());
        System.out.println("It has the following categories: " + myProject2.getCategories());
        System.out.println("The main idea is: " + myProject2.getProjectPurpose());
        System.out.println("The myProject2 Owner is: " + myProject2.getProjectOwner());
        System.out.println("The main audience is: " + myProject2.getProjectTargetAudience());
        System.out.println("The following should be completed: " + myProject2.getProjectActivities());
        System.out.println("The budget allocated is: " + myProject2.getProjectBudget());
        System.out.println("The time span is from: " + myProject2.getProjectTimeSpanFrom() + " to " + myProject2.getProjectTimeSpanTo());
        System.out.println("Closest deadline funds for myProject2: " + myProject2.getClosestDeadlineFunds());

    // Print initial list of funds
    System.out.println("Initial list of funds:");
    for (fundClass funde : fundList) {
        System.out.println(funde.getTitle());
    }
    sortingFundDeadlines sorter = new sortingFundDeadlines();
    sorter.sortFundsForCloesestDeadlines(fundList, myProject2, true);
        
}
}
