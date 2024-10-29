import java.util.ArrayList;

public class sortingFundDeadlines {
    public ArrayList<fundClass> sortFundsForCloesestDeadlines(ArrayList<fundClass> fundList, project project, boolean onlyOneIsNeeded){
        removedPassedDeadline removerOfDeadlines = new removedPassedDeadline();
        compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
        ArrayList<fundClass> tempfundList = comparer.compareCategoriesWithFund(onlyOneIsNeeded, removerOfDeadlines.removePassedDeadlines(fundList), project);
            // Sort the funds based on the furthest deadline

        // Print unsorted funds by furthest deadline
        System.out.println("Before sorting:");
        for (fundClass fund : tempfundList) {

            System.out.println(fund.getDeadlines() + " " + fund.getTitle());
        }
        fundQSort sortingQ = new fundQSort();
        sortingQ.fundQuickSort(tempfundList);

        // Print sorted funds by furthest deadline
        System.out.println("\nAfter sorting:");
        for (fundClass fund : tempfundList) {
            System.out.println(fund.getDeadlines() + " " + fund.getTitle());
        }
        return tempfundList;
    }
}
