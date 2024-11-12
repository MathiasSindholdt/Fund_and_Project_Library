import java.util.ArrayList;
import java.util.Objects;
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
        ArrayList<fundClass> funds = new ArrayList<>();
        ArrayList<fundClass> expectedFunds = new ArrayList<>();

        project project = new project();
        project.setCategories("cat1");

        fundClass fund1 = new fundClass();
        fund1.setTitle("fudn1");
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        fund1.setRunning(false);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        fund1.setCategories("cat1");
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setTitle("fudn2");
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2023, 11, 21, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2003, 11, 21, 12, 0));
        fund2.setRunning(false);
        fund2.setCategories("cat1");
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setTitle("fund3");
        fund3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2023, 11, 21, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2022, 11, 21, 12, 0));
        fund3.setRunning(false);
        fund3.setCategories("cat1");
        funds.add(fund3);

        // Print unsorted funds by closest deadline
        System.out.println("Before sorting:");
        for (fundClass fund : funds) {
            System.out.println(fund.getTitle() + " + " + fund.getDeadlines());
        }

        // Apply quickSort on the list of funds
        sortingFundLists sorter = new sortingFundLists();
        funds = sorter.sortFunds(funds, project, false);

        // Print sorted funds by closest deadline
        System.out.println("\nAfter sorting:");
        for (fundClass fund : funds) {
            System.out.println(fund.getTitle() + " + " + fund.getDeadlines());
        }
    }
}