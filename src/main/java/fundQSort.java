import java.time.LocalDateTime;
import java.util.ArrayList;

public class fundQSort {

    // QuickSort method to clean and sort in place
    public static void fundQuickSort(ArrayList<fundClass> funds) {
        // Remove expired funds directly from the original list
        funds.removeIf(fund -> allDeadlinesPassed(fund));

        // Sort the cleaned list in place
        quickSortHelper(funds, 0, funds.size() - 1);
    }

    // Helper method to check if all deadlines have passed
    public static boolean allDeadlinesPassed(fundClass fund) {
        LocalDateTime now = LocalDateTime.now();
        for (LocalDateTime deadline : fund.getDeadlines()) {
            if (deadline.isAfter(now)) {
                return false; // Found a future deadline
            }
        }
        return true; // All deadlines are in the past
    }

    // Recursive helper for quicksort
    private static void quickSortHelper(ArrayList<fundClass> funds, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(funds, low, high);
            quickSortHelper(funds, low, pivotIndex - 1);
            quickSortHelper(funds, pivotIndex + 1, high);
        }
    }

    // Partition method
    private static int partition(ArrayList<fundClass> funds, int low, int high) {
        fundClass pivot = funds.get(high); // Choose the last element as the pivot
        LocalDateTime pivotDateCreated = pivot.getDateCreated();
        LocalDateTime pivotDeadline = getClosestDeadline(pivot);
        if (pivot.getRunning() == true){
            pivotDateCreated = pivot.getDateCreated();
        } else {
            pivotDeadline = getClosestDeadline(pivot);
        }

        if (pivot.getRunning() == true){
            int i = low - 1; // Index of the smaller element
            for (int j = low; j < high; j++) {
            LocalDateTime currentFundCreationDate = funds.get(j).getDateCreated();

            // Compare deadlines and swap if current fund's deadline is before the pivot's
            if (currentFundCreationDate.isBefore(pivotDateCreated) ||
                currentFundCreationDate.isEqual(pivotDateCreated)) {
                    i++;
                    swap(funds, i, j);
                }
            }
            // Swap funds[i + 1] and pivot (funds[high])
            swap(funds, i + 1, high);
            return i + 1;
        } else {
            // This sorts the list based on closest deadline
            int i = low - 1; // Index of the smaller element
            for (int j = low; j < high; j++) {
            LocalDateTime currentFundDeadline = getClosestDeadline(funds.get(j));
            // Compare deadlines and swap if current fund's deadline is before the pivot's
            if (currentFundDeadline.isBefore(pivotDeadline) || currentFundDeadline.isEqual(pivotDeadline)) {
                    i++;
                    swap(funds, i, j);
                }
            }
            // Swap funds[i + 1] and pivot (funds[high])
            swap(funds, i + 1, high);
            return i + 1;
        }
    }

    // Helper method to swap elements in the ArrayList
    private static void swap(ArrayList<fundClass> funds, int i, int j) {
        fundClass temp = funds.get(i);
        funds.set(i, funds.get(j));
        funds.set(j, temp);
    }

    // Method to get the closest future deadline from a fund
    public static LocalDateTime getClosestDeadline(fundClass fund) {
        ArrayList<LocalDateTime> deadlines = fund.getDeadlines();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime closest = null;

        for (LocalDateTime deadline : deadlines) {
            // Check if the deadline is in the future
            if (deadline.isAfter(now)) {
                // Set closest to the first future deadline, or update if this deadline is closer
                if (closest == null || deadline.isBefore(closest)) {
                    closest = deadline;
                }
            }
        }
        return closest;
    }

    // Test method
    public static void main(String[] args) {
        // Test data setup
        ArrayList<fundClass> funds = new ArrayList<>();

        fundClass fund1 = new fundClass();
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0)); // Single deadline
        fund1.setRunning(false);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0)); // Two deadlines
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0)); // Single deadline
        fund2.setDeadlines(LocalDateTime.of(2026, 11, 21, 12, 0)); // Two deadlines
        fund2.setRunning(false);
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setDateCreated(LocalDateTime.of(2023, 9, 5, 12, 0)); // Single deadline
        fund3.setDeadlines(LocalDateTime.of(2022, 11, 21, 12, 0)); // Two deadlines
        fund3.setDeadlines(LocalDateTime.of(2025, 11, 21, 12, 0)); // Two deadlines
        fund3.setRunning(false);
        funds.add(fund3);

        // Print unsorted funds by closest deadline
        System.out.println("Before sorting:");
        for (fundClass fund : funds) {
            System.out.println(fund.getDateCreated() + " + " + fund.getDeadlines());
        }

        // Apply quickSort on the list of funds
        fundQuickSort(funds);

        // Print sorted funds by closest deadline
        System.out.println("\nAfter sorting:");
        for (fundClass fund : funds) {
            System.out.println(fund.getDateCreated() + " + " + fund.getDeadlines());
        }
    }
}