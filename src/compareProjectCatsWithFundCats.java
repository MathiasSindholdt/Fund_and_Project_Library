import java.util.ArrayList;

public class compareProjectCatsWithFundCats {
    public ArrayList<fundClass> compareCategoriesWithFund(boolean onlyOneIsNeeded, ArrayList<fundClass> fundList, project project) {
        ArrayList<fundClass> tempList = new ArrayList<>();
        for(fundClass fund : fundList){
            boolean flagPresent = false;
            // Iterate through 'this' categories
            for (String category : project.getCategories()) {
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
                    for (String thisCategory : project.getCategories()) {
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
}
