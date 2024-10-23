import java.time.LocalDateTime;
import java.util.ArrayList;

public class fundQSort {
    
    // QuickSort method
    public static void fundQuickSort(ArrayList<fundClass> funds) {
        quickSortHelper(funds, 0, funds.size() - 1);
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
        LocalDateTime pivotDeadline = getFurthestDeadline(pivot);

        int i = low - 1; // Index of the smaller element
        for (int j = low; j < high; j++) {
            LocalDateTime currentFundDeadline = getFurthestDeadline(funds.get(j));

            // Compare deadlines and swap if current fund's deadline is before the pivot's
            if (currentFundDeadline.isBefore(pivotDeadline) ||
                currentFundDeadline.isEqual(pivotDeadline)) {
                i++;
                swap(funds, i, j);
            }
        }
        // Swap funds[i + 1] and pivot (funds[high])
        swap(funds, i + 1, high);
        return i + 1;
    }

    // Helper method to swap elements in the ArrayList
    private static void swap(ArrayList<fundClass> funds, int i, int j) {
        fundClass temp = funds.get(i);
        funds.set(i, funds.get(j));
        funds.set(j, temp);
    }

    // Method to get the furthest deadline from a fund
    private static LocalDateTime getFurthestDeadline(fundClass fund) {
        ArrayList<LocalDateTime> deadlines = fund.getDeadlines();
        if (deadlines.isEmpty()) {
            return LocalDateTime.MIN; // Return the earliest possible date if no deadlines
        }

        LocalDateTime furthest = deadlines.get(0); // Start with the first deadline
        for (LocalDateTime deadline : deadlines) {
            if (deadline.isAfter(furthest)) {
                furthest = deadline;
            }
        }
        return furthest;
    }

    // Test method
    public static void main(String[] args) {
        // Test data setup
        ArrayList<fundClass> funds = new ArrayList<>();

        fundClass fund1 = new fundClass();
        fund1.setDeadlines(LocalDateTime.of(2024, 11, 20, 12, 0)); // Single deadline
        fund1.setDeadlines(LocalDateTime.of(2025, 11, 21, 12, 0)); // Two deadlines
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setDeadlines(LocalDateTime.of(2024, 12, 15, 12, 0)); // Single deadline
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setDeadlines(LocalDateTime.of(2024, 9, 5, 12, 0)); // Single deadline
        funds.add(fund3);

        // Print unsorted funds by furthest deadline
        System.out.println("Before sorting:");
        for (fundClass fund : funds) {
            System.out.println(getFurthestDeadline(fund));
        }

        // Apply quickSort on the list of funds
        fundQuickSort(funds);

        // Print sorted funds by furthest deadline
        System.out.println("\nAfter sorting:");
        for (fundClass fund : funds) {
            System.out.println(getFurthestDeadline(fund));
        }
    }
}
