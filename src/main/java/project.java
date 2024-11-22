import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class project extends projectAbstract {
    private ArrayList<fundClass> closestDeadlineFunds = new ArrayList<fundClass>();
    private fundClass assignedFund;

    public int assignFundFromName(String Name){
        for (fundClass fc : main.fundList){
            if (Objects.equals(Name, fc.getTitle())){
                assignedFund=fc;
                return 0;
            }
        }
        System.out.println("no matching funds found");
        return 1;
    }

    public void assignFund(fundClass newFund)
    {
        assignedFund=newFund;
    }

    public fundClass getFund()
    {
        return assignedFund;
    }

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
// public static void main(String[] args) {
// //     public static void main(String[] args) {

}
//         // Add funds to list
//     ArrayList<fundClass> listOfFunds = new ArrayList<>();
//     listOfFunds.add(fund1);
//     listOfFunds.add(fund2);
//     listOfFunds.add(fund3);

//     // Instantiate project and call removePassedDeadlines method
//     project myProject = new project();

//     // Print initial list of funds
//     System.out.println("Initial list of funds:");
//     for (fundClass funde : fundList) {
//         System.out.println(funde.getTitle());
//     }



//     sortingFundLists sorter = new sortingFundLists();
//     sorter.sortFunds(listOfFunds, myProject, false);


// }
// }
