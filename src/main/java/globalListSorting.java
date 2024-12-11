import java.util.ArrayList;

public class globalListSorting {

    public ArrayList<proposalProject> compareTitleProposal(ArrayList<proposalProject> sortedList) {
        System.out.println("Title a-z Proposal");
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
        System.out.println("Title z-a Proposal");
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
        System.out.println("Owner a-z Proposal");
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
        System.out.println("owner z-a Proposal");
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
        System.out.println("Newest Proposal");
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

    // Sorts from furthest to closest date
    public ArrayList<proposalProject> sortByFurthestDateProposal(ArrayList<proposalProject> sortedList) {
        System.out.println("Furthest Proposal");
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

    public ArrayList<project> compareTitleProject(ArrayList<project> sortedList) {
        System.out.println("Title a-z Project");
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
        System.out.println("Title z-a Project");
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
        System.out.println("Owner a-z Project");
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
        System.out.println("Owner z-a Project");
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
        System.out.println("Closest Project");
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

    // Sorts from furthest to closest date
    public ArrayList<project> sortByFurthestDateProject(ArrayList<project> sortedList) {
        System.out.println("Furthest Project");
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

    public ArrayList<fundClass> compareTitleFund(ArrayList<fundClass> sortedList) {
        System.out.println("Title a-z Fund");
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
        System.out.println("Title z-a Fund");
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

    public ArrayList<fundClass> compareBudgetFundDecreasing(ArrayList<fundClass> sortedList ) {
        System.out.println("Budget Decrasing Fund");
        int n = sortedList.size();
        fundClass temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getBudgetMax() > sortedList.get(j).getBudgetMax()) {
                    // Swap elements
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }
        }
        return sortedList;
    }

    public ArrayList<fundClass> compareBudgetFundIncreasing(ArrayList<fundClass> sortedList ) {
        System.out.println("Budget Increasing Fund");
        int n = sortedList.size();
        fundClass temp;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compare titles of the two elements in reverse order
                if (sortedList.get(i).getBudgetMax() < sortedList.get(j).getBudgetMax()) {
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
            System.out.println("Closest Deadline Fund");
            ArrayList<fundClass> fullList = new ArrayList<>();
            fullList.addAll(sortedList);
            fundQSort.fundQuickSort(sortedList);
            for (fundClass fC : fullList){
                if(!(sortedList.contains(fC))){
                    sortedList.add(fC);
                }
            }
            return sortedList;
        }

        // Sorts from closest to furthest date
        public ArrayList<fundClass> sortFundByFurthestDeadline(ArrayList<fundClass> sortedList) {
            System.out.println("Furthest Deadline Fund");
            ArrayList<fundClass> fullList = new ArrayList<>();
            fullList.addAll(sortedList);
            fundQSort.fundQuickSort(sortedList);
            for (fundClass fC : fullList){
                if(!(sortedList.contains(fC))){
                    sortedList.add(fC);
                }
            }
            ArrayList<fundClass> reversedList = new ArrayList<>();
            for (int i = sortedList.size() - 1; i >= 0; i--) {
                reversedList.add(sortedList.get(i));
            }
            return reversedList;
        }

}