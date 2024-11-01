import java.util.ArrayList;

public class compareProjectCatsWithFundCats {

    public ArrayList<fundClass> compareCategoriesWithFund(boolean onlyOneIsNeeded, ArrayList<fundClass> fundList, project project) {

        // Initialize a new ArrayList for the result
        ArrayList<fundClass> tempList = new ArrayList<>();

        for (fundClass fund : fundList) {
            boolean flagPresent = onlyOneIsNeeded ? false : true;  // Initialize based on `onlyOneIsNeeded`

            // Iterate through 'project' categories

            for (String category : project.getCategories()) {
                if (onlyOneIsNeeded) {
                    // If any one category matches, add the fund and break
                    if (fund.getCategories().contains(category)) {
                        System.out.println("Category '" + category + "' was present in both the project and the fund.");
                        flagPresent = true;
                        break;
                    }
                } else {
                    // Check if the fund does NOT contain the current category
                    if (!fund.getCategories().contains(category)) {
                        System.out.println("Category '" + category + "' is missing in the fund.");
                        flagPresent = false;
                        break; // Exit early if a mismatch is found
                    }
                }
            }

            // Only add to tempList if flagPresent is still true after the checks
            if (flagPresent) {
                tempList.add(fund);
            }
        }
        return tempList;
    }
}
