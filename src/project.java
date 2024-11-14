import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        sortingFundLists sorter = new sortingFundLists();
        closestDeadlineFunds =  sorter.sortFunds(fundList, this, onlyOneIsNeeded);
    }

    public static void main(String[] args) {
        ArrayList<String> testCategories = new ArrayList<>(Arrays.asList("Byg", "Trivsel", "cat3"));
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
        fund0.setBudget(30250, 232000);
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
        fund1.setBudget(1000, 100002);
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
        fund2.setTitle("fundTitle2");
        fund2.setDescription("Lorem Ipsum");
        fund2.setBudget(100000,80000000);
        String[] fund2Catories = {"cat1","cat2","cat3"};
        for (int i = 0; i < fund2Catories.length ; i++){
            fund2.setCategories(fund2Catories[i]);
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

        //test fund3
        String[] fund3Catories = {"cat1","cat2","cat3"};
        LocalDateTime[] fund3Deadlines = {LocalDateTime.parse("2024-10-04T12:00:00")};
        String[] fund3Contacts = {"lucas","lundse"};
        String[] fund3CollaborationHistory = {"P1","P2"};

        fundClass fund3 = new fundClass();
        fund3.setTitle("fundTitle3");
        fund3.setDescription("Lorem Ipsum");
        fund3.setBudget(100000,80000000);
        for (int i = 0; i < fund3Catories.length ; i++){
            fund3.setCategories(fund3Catories[i]);
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
        



        // Add funds to list
        ArrayList<fundClass> listOfFunds = new ArrayList<>();
        listOfFunds.add(fund1);
        listOfFunds.add(fund2);
        listOfFunds.add(fund3);

        project myProject = new project(
                "Community Project",
                testCategories,
                "Project to enhance community resources",
                "To improve quality of life",
                "Jane Doe",
                "Local Residents",
                50000L,
                LocalDateTime.parse("2024-01-01T09:00:00"),
                LocalDateTime.parse("2024-12-31T17:00:00"),
                "Various activities",
                fundList,
                false
            );

        // Print initial list of funds
        System.out.println("Initial list of funds:");
        for (fundClass funde : fundList) {
            System.out.println(funde.getTitle());
        }



        sortingFundLists sorter = new sortingFundLists();
        sorter.sortFunds(listOfFunds, myProject, false);

        List<project> projects = new ArrayList<>();
        projects.add(myProject); // Add your project(s) here

        
        String filepath = "projects.csv";
        ProjectCsvWriter.writeProjectCsv(filepath, projects);
        
        List<project> readProjects = ProjectCsvReader.readProjectCsv(filepath);
        System.out.println("Projects read from projects.csv");
        for (project proj : readProjects) {
                System.out.println("Title: " + proj.getTitle());
                System.out.println("Categories: " + String.join(", ", proj.getCategories()));
                System.out.println("Description: " + proj.getDescription());
                System.out.println("Purpose: " + proj.getProjectPurpose());
                System.out.println("Owner: " + proj.getProjectOwner());
                System.out.println("Target Audience: " + proj.getProjectTargetAudience());
                System.out.println("Budget: " + proj.getProjectBudget());
                System.out.println("TimeSpan From: " + proj.getProjectTimeSpanFrom());
                System.out.println("TimeSpan To: " + proj.getProjectTimeSpanTo());
                System.out.println("Activities: " + proj.getProjectActivities());
                System.out.println();
        }

    }
}
