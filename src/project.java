import java.time.LocalDateTime;
import java.util.ArrayList;

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
        //test fund1
        String[] fundCatories = {"cat1","cat2","cat3"};
        LocalDateTime[] fundDeadlines = {LocalDateTime.parse("2024-12-06T12:00:00"),LocalDateTime.parse("2024-12-08T12:00:00")};
        String[] fundContacts = {"lucas","lundse"};
        String[] fundCollaborationHistory = {"P1","P2"};

        fundClass fund = new fundClass();
        fund.setTitle("fundTitle");
        fund.setDescription("Lorem Ipsum");
        fund.setBudget(100000,80000000);
        for (int i = 0; i < fundCatories.length ; i++){
            fund.setCategories(fundCatories[i]);
        }
        for (int i = 0; i < fundDeadlines.length ; i++){
            fund.setDeadlines(fundDeadlines[i]);
        }
        for (int i = 0; i < fundContacts.length ; i++){
            fund.setContacts(fundContacts[i]);
        }
        for (int i = 0; i < fundCollaborationHistory.length ; i++){
            fund.setCollaborationHistory(fundCollaborationHistory[i]);
        }

        //test fund2
        String[] fund2Catories = {"cat1","cat2","cat3"};
        LocalDateTime[] fund2Deadlines = {LocalDateTime.parse("2024-12-04T12:00:00")};
        String[] fund2Contacts = {"lucas","lundse"};
        String[] fund2CollaborationHistory = {"P1","P2"};

        fundClass fund2 = new fundClass();
        fund2.setTitle("fundTitle2");
        fund2.setDescription("Lorem Ipsum");
        fund2.setBudget(100000,80000000);
        for (int i = 0; i < fund2Catories.length ; i++){
            fund2.setCategories(fund2Catories[i]);
        }
        for (int i = 0; i < fund2Deadlines.length ; i++){
            fund2.setDeadlines(fund2Deadlines[i]);
        }
        for (int i = 0; i < fund2Contacts.length ; i++){
            fund2.setContacts(fund2Contacts[i]);
        }
        for (int i = 0; i < fund2CollaborationHistory.length ; i++){
            fund2.setCollaborationHistory(fund2CollaborationHistory[i]);
        }

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
        for (int i = 0; i < fund3Deadlines.length ; i++){
            fund3.setDeadlines(fund3Deadlines[i]);
        }
        for (int i = 0; i < fund3Contacts.length ; i++){
            fund3.setContacts(fund3Contacts[i]);
        }
        for (int i = 0; i < fund3CollaborationHistory.length ; i++){
            fund3.setCollaborationHistory(fund3CollaborationHistory[i]);
        }

        //TestProject
        String[] projectCatories = {"Byg","Trivsel","cat3"};
  
        project project = new project();
        project.setTitle("Project");
        project.setDescription("Lorem Ipsum");
        project.setProjectBudget(80000000);
        for (int i = 0; i < projectCatories.length ; i++){
            project.setCategories(projectCatories[i]);
        }
        project.setTimeSpan(LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        //project.setTimeSpan(); //This also works
        project.setProjectPurpose("Here is the main idea");
        project.setProjectOwner("Lucas");
        project.setProjectTargetAudience("Techcollege med mere");
        project.setProjectActivities("Her er noget vi skal have gjort");

        LocalDateTime timeSpanproject[] = {project.getProjectTimeSpanFrom(),project.getProjectTimeSpanTo()};
        System.out.println("TYPE: " + project.getClass());
        System.out.println("Here is the project: " + project.getTitle());
        System.out.println("It has the following description: " + project.getDescription());
        System.out.println("It was created at: " + project.getDateCreated());
        System.out.println("It has the following categoreis: " + project.getCategories());
        System.out.println("The main idea is: " + project.getProjectPurpose());
        System.out.println("The Project Owner is: " + project.getProjectOwner());
        System.out.println("The main audience is: " + project.getProjectTargetAudience());
        System.out.println("The span of the project is from: " + timeSpanproject[0] + "and to: " + timeSpanproject[1]);
        System.out.println("The following should be completed: " + project.getProjectActivities());



        // Add funds to list
    ArrayList<fundClass> listOfFunds = new ArrayList<>();
    listOfFunds.add(fund);
    listOfFunds.add(fund2);
    listOfFunds.add(fund3);

    // Instantiate project and call removePassedDeadlines method
    project myProject = new project();

    // Print initial list of funds
    System.out.println("Initial list of funds:");
    for (fundClass funde : listOfFunds) {
        System.out.println(funde.getTitle());
    }
    sortingFundDeadlines sorter = new sortingFundDeadlines();
    sorter.sortFundsForCloesestDeadlines(listOfFunds, myProject, false);
}
}
