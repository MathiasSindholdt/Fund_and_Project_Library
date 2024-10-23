import java.time.LocalDateTime;
import java.util.ArrayList;

public class project extends projectAbstract {
    ArrayList<fundClass> ClosestDeadlineFunds = new ArrayList<fundClass>();


    public void sortFundsForCloesestDeadlines(ArrayList<fundClass> fundList){
        ArrayList<fundClass> tempfundList = compareCategoriesWithFund(true, removePassedDeadlines(fundList));
            // Sort the funds based on the furthest deadline

        // Print unsorted funds by furthest deadline
        System.out.println("Before sorting:");
        for (fundClass fund : tempfundList) {
            System.out.println(fund.getDeadlines());
        }
        fundQSort sorting = new fundQSort();
        sorting.fundQuickSort(tempfundList);

        // Print sorted funds by furthest deadline
        System.out.println("\nAfter sorting:");
        for (fundClass fund : tempfundList) {
            System.out.println(fund.getDeadlines());
        }
    }
   
    public ArrayList<fundClass> removePassedDeadlines(ArrayList<fundClass> listOfFunds) {
        ArrayList<fundClass> withinTimespan = new ArrayList<>();
        LocalDateTime dateChecked = LocalDateTime.now().minusDays(60);
        
        for (fundClass fund : listOfFunds) {
            boolean added = false;  // Flag to ensure fund is only added once
            for (LocalDateTime deadline : fund.getDeadlines()) {
                if (deadline.isAfter(dateChecked) && !added) {
                    System.out.println("Found a deadline further out: " + deadline + " for fund: " + fund.getTitle());
                    withinTimespan.add(fund);
                    added = true;  // Ensure the fund is added only once
                }
            }
        }
        return withinTimespan;
    }  

public ArrayList<fundClass> compareCategoriesWithFund(boolean onlyOneIsNeeded, ArrayList<fundClass> fundList) {
    ArrayList<fundClass> tempList = fundList;
    for(fundClass fund : fundList){
        boolean flagPresent = false;
        // Iterate through 'this' categories
        for (String category : this.getCategories()) {
            // If you only need to check for one match, break out of the loop
            if (onlyOneIsNeeded) {
                // Check if the fund's categories contain the current category from 'this'
                if (fund.getCategories().contains(category)) {
                    System.out.println("Category '" + category + "' was present in both 'this' and the fund.");
                    flagPresent = true;
                    tempList.add(fund);
                    break;
                }
            } else if (!onlyOneIsNeeded) {
                flagPresent = true; // Assume all will match unless we find a mismatch
                // Check if the fund does NOT contain the current category
                for (String thisCategory : this.getCategories()) {
                    if (!fund.getCategories().contains(thisCategory)) {
                        System.out.println("Category '" + thisCategory + "' is missing in the fund.");
                        flagPresent = false;  // Set flag to false if any category is missing
                        break;  // Exit early as all categories must match
                    }
                }
                // If all categories matched
                if (flagPresent) {
                    System.out.println("All categories match.");
                    tempList.add(fund);
                }
            }
        }
        System.out.println("Final flag state: " + flagPresent);
    }
    return tempList;
}
    

    
    public static void main(String[] args) {
        //test fund1
        String[] fundCatories = {"cat1","cat2","cat3"};
        LocalDateTime[] fundDeadlines = {LocalDateTime.parse("2024-10-02T12:00:00"),LocalDateTime.parse("2024-12-08T12:00:00")};
        String[] fundContacts = {"lucas","lundse"};
        String[] fundCollaborationHistory = {"P1","P2"};

        fundClass fund = new fundClass();
        fund.setTitle("fundTitle");
        fund.setDescription("Lorem Ipsum");
        fund.setCommonBudget(80000000);
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
        fund2.setCommonBudget(80000000);
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
        fund3.setCommonBudget(80000000);
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

    project.compareCategoriesWithFund(false, listOfFunds);

    // Instantiate project and call removePassedDeadlines method
    project myProject = new project();

    // Print initial list of funds
    System.out.println("Initial list of funds:");
    for (fundClass funde : listOfFunds) {
        System.out.println(funde.getTitle());
    }

    // Call removePassedDeadlines and capture the valid funds
    ArrayList<fundClass> validFunds = myProject.removePassedDeadlines(listOfFunds);

    // Print removed funds (those that have passed deadlines)
    System.out.println("\nFunds with passed deadlines removed:");
    for (fundClass funde : listOfFunds) {
        if (!validFunds.contains(funde)) {
            System.out.println(funde.getTitle());
        }
    }

    // Print final list of funds with valid deadlines
    System.out.println("\nFinal list of funds with future deadlines:");
    for (fundClass funde : validFunds) {
        System.out.println(funde.getTitle());
    }

    myProject.sortFundsForCloesestDeadlines(listOfFunds);

}
}