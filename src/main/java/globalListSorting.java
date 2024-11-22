import java.util.ArrayList;

public class globalListSorting {
    public ArrayList<proposalProject> sortProposalList(
        boolean titleAlphabetic, 
        boolean ownerAplhabetic, 
        boolean dateCreatedOldestFirst, 
        boolean titleReverseAlphabetic,
        boolean ownerReverseAlphabetic,
        ArrayList<proposalProject> globalProposalList
        ){
        ArrayList<proposalProject> sortedList = new ArrayList<>(globalProposalList);
        // Determine which sorting condition is selected
        if (titleAlphabetic){return compareTitleProposal(sortedList);}
        else if (ownerAplhabetic){return compareOwnerProposal(sortedList);}
        else if (dateCreatedOldestFirst){return sortByFurthestDateProposal(sortedList);}
        else if (titleReverseAlphabetic){return compareTitleProposalReverse(sortedList);}
        else if (ownerReverseAlphabetic){return compareReverseOwnerProposal(sortedList);}
        else{return sortByClosestDateProposal(sortedList);}
    }
    

    public ArrayList<project> sortProjectList(
        boolean titleAlphabetic, 
        boolean ownerAplhabetic, 
        boolean dateCreatedOldestFirst,
        boolean closestDeadline,
        boolean titleReverseAlphabetic,
        boolean ownerReverseAlphabetic,
        ArrayList<project> globalProjectList)
        {
            ArrayList<project> sortedList = new ArrayList<>(globalProjectList);
            // Determine which sorting condition is selected
            if (titleAlphabetic){return compareTitleProject(sortedList);}
            else if (ownerAplhabetic){return compareOwnerProject(sortedList);}
            else if (dateCreatedOldestFirst){return sortByFurthestDateProject(sortedList);}
            else if (closestDeadline){return sortByClosestDateProject(sortedList);} //IMPLEMENT NAAR MAN KAN GEMME EN BEVILLIGET FOND! Lige nu er det bare paa dato
            else if (titleReverseAlphabetic){return compareTitleReverseProject(sortedList);}
            else if (ownerReverseAlphabetic){return compareOwnerReverseProject(sortedList);}
            else{return sortByClosestDateProject(sortedList);}
        }

    public ArrayList<fundClass> sortFundList(
        boolean titleAlphabetic, 
        boolean dateCreatedOldestFirst,
        boolean closestDeadline, 
        boolean titleReverseAlphabetic,
        boolean ownerReverseAlphabetic,
        ArrayList<fundClass> globalProjectList)
        {
            ArrayList<fundClass> sortedList = new ArrayList<>(globalProjectList);
            // Determine which sorting condition is selected
            if (titleAlphabetic){return compareTitleFund(sortedList);}
            else if (dateCreatedOldestFirst){return sortByFurthestDateFund(sortedList);}
            else if (closestDeadline){return sortFundByClosestDeadline(sortedList);} //IMPLEMENT NAAR MAN KAN GEMME EN BEVILLIGET FOND! Lige nu er det bare paa dato
            else if (titleReverseAlphabetic){return compareTitleReverseFund(sortedList);}
            else{return sortByClosestDateFund(sortedList);}
        }


    public ArrayList<proposalProject> compareTitleProposal(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        proposalProject temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) > 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<proposalProject> compareTitleProposalReverse(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        proposalProject temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) < 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<proposalProject> compareOwnerProposal(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        proposalProject temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements
                if (sortedList.get(i).getProjectOwner().toLowerCase().compareTo(sortedList.get(j).getProjectOwner().toLowerCase()) > 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<proposalProject> compareReverseOwnerProposal(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        proposalProject temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getProjectOwner().toLowerCase().compareTo(sortedList.get(j).getProjectOwner().toLowerCase()) < 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    // Sorts from closest to furthest date
    public ArrayList<proposalProject> sortByClosestDateProposal(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) > 0) {
                    // Manual swap
                    proposalProject temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    // Sorts from furthest to closest date
    public ArrayList<proposalProject> sortByFurthestDateProposal(ArrayList<proposalProject> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) < 0) {
                    // Manual swap
                    proposalProject temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<project> compareTitleProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        project temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) > 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<project> compareTitleReverseProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        project temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) < 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<project> compareOwnerProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        project temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements
                if (sortedList.get(i).getProjectOwner().toLowerCase().compareTo(sortedList.get(j).getProjectOwner().toLowerCase()) > 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<project> compareOwnerReverseProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        project temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getProjectOwner().toLowerCase().compareTo(sortedList.get(j).getProjectOwner().toLowerCase()) < 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    // Sorts from closest to furthest date
    public ArrayList<project> sortByClosestDateProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) > 0) {
                    // Manual swap
                    project temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    // Sorts from furthest to closest date
    public ArrayList<project> sortByFurthestDateProject(ArrayList<project> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) < 0) {
                    // Manual swap
                    project temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<fundClass> compareTitleFund(ArrayList<fundClass> sortedList) {
        int n = sortedList.size();
        fundClass temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) > 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<fundClass> compareTitleReverseFund(ArrayList<fundClass> sortedList ) {
        int n = sortedList.size();
        fundClass temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getTitle().toLowerCase().compareTo(sortedList.get(j).getTitle().toLowerCase()) < 0) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    

    // Sorts from closest to furthest date
    public ArrayList<fundClass> sortFundByClosestDeadline(ArrayList<fundClass> sortedList) {
        fundQSort.fundQuickSort(sortedList);
        return sortedList;
    }
    

    // Sorts from furthest to closest date
    public ArrayList<fundClass> sortByFurthestDateFund(ArrayList<fundClass> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) < 0) {
                    // Manual swap
                    fundClass temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    // Sorts from furthest to closest date
    public ArrayList<fundClass> sortByClosestDateFund(ArrayList<fundClass> sortedList) {
        int n = sortedList.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare dates
                if (sortedList.get(i).getDateCreated().compareTo(sortedList.get(j).getDateCreated()) < 0) {
                    // Manual swap
                    fundClass temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }
}