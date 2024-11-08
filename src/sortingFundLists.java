import java.util.ArrayList;
import java.time.LocalDateTime;

public class sortingFundLists {
    public ArrayList<fundClass> sortFunds(ArrayList<fundClass> fundList, project project, boolean onlyOneIsNeeded){
        compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
        ArrayList<fundClass> tempfundList = comparer.compareCategoriesWithFund(onlyOneIsNeeded, fundList, project);
        // Sort the funds based on the furthest deadline

        fundQSort sortingQ = new fundQSort();
        sortingQ.fundQuickSort(tempfundList);

        return tempfundList;
    }


    public static void main(String[] args) {
        // Test data setup
        ArrayList<fundClass> funds = new ArrayList<>();
        String[] testCategories = {"cat1", "cat2", "cat3"};
    
        // Setting up fund objects
        fundClass fund1 = new fundClass();
        fund1.setDateCreated(LocalDateTime.of(2030, 11, 20, 12, 0));
        fund1.setRunning(true);
        for(String category : testCategories){
            fund1.setCategories(category);
        }
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        funds.add(fund1);
    
        fundClass fund2 = new fundClass();
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2026, 11, 21, 12, 0));
        fund2.setCategories("cat2");
        fund2.setRunning(true);
        funds.add(fund2);
    
        fundClass fund3 = new fundClass();
        fund3.setDateCreated(LocalDateTime.of(2029, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        for(String category : testCategories){
            fund3.setCategories(category);
        }
        fund3.setRunning(true);
        funds.add(fund3);
    
        // Setting up project with categories
        project project = new project();
        project.setTitle("Project");
        project.setDescription("Lorem Ipsum");
        project.setProjectBudget(80000000);
        for (String category : testCategories) {
            project.setCategories(category);
        }
        project.setTimeSpan(LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        project.setProjectPurpose("Here is the main idea");
        project.setProjectOwner("Lucas");
        project.setProjectTargetAudience("Techcollege med mere");
        project.setProjectActivities("Her er noget vi skal have gjort");

        System.out.println("Before sorting 1:");
        for (fundClass fund : funds) {
            System.out.println(fund.getDeadlines());
    
        // Sort and filter the list of funds
        sortingFundLists sorter = new sortingFundLists();
        ArrayList<fundClass> filteredAndSortedFunds = sorter.sortFunds(funds, project, true);

        // Print sorted funds by closest deadline
        System.out.println("\nAfter sorting 1:");
        for (fundClass testFund : filteredAndSortedFunds) {
            System.out.println(testFund.getDeadlines());
        }
        }
    }
}