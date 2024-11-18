import java.util.ArrayList;

public class SortMainLists {
    //Overloaded sorter
    public ArrayList<proposalProject> sortProposals(ArrayList<proposalProject> unsortedList, boolean creationDate, boolean title) {
        // Make a copy of the list to avoid modifying the original
        ArrayList<proposalProject> sortedListWithoutCats = new ArrayList<>(unsortedList);

        if (creationDate) {
            // Sort by creation date in descending order
            sortedListWithoutCats.sort((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()));
        } else if (title) {
            // Sort by title alphabetically
            sortedListWithoutCats.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            // Return the unsorted list
            return unsortedList;
        }

        return sortedListWithoutCats;
    }

    public ArrayList<proposalProject> sortProposals(ArrayList<proposalProject> unsortedList, boolean creationDate, boolean title, ArrayList<String> categories) {
        // Make a copy of the list to avoid modifying the original

        ArrayList<proposalProject> unsortedMatchingCats = new ArrayList<>();
        for (proposalProject proposal : unsortedList){
            if (proposal.getCategories().containsAll(categories)){
                unsortedMatchingCats.add(proposal);
            }
        }

        if (creationDate) {
            // Sort by creation date in descending order
            unsortedMatchingCats.sort((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()));
        } else if (title) {
            // Sort by title alphabetically
            unsortedMatchingCats.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            // Return the unsorted list
            return unsortedMatchingCats;
        }
        return unsortedList;
    }
}
