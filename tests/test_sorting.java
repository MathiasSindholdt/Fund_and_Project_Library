import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class test_sorting extends testTemplate {

    public String ErrDump = new String();

    public boolean test() {
        this.TestName = "Sorting Test";
        if (!testSorting()) {
            this.ErrMessage += " Error in Sorting test\n";
            this.ErrMessage += ErrDump;

            return false;
        }

        return true;

    }

    public boolean testSorting() {

        boolean passed = true;
        ArrayList<fundClass> funds = new ArrayList<>();
        ArrayList<fundClass> expectedFunds = new ArrayList<>();

        project project = new project();
        project.setCategories("cat1");

        fundClass fund1 = new fundClass();
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        fund1.setRunning(false);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        fund1.setCategories("cat1");
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2026, 11, 21, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2029, 11, 21, 12, 0));
        fund2.setRunning(false);
        fund2.setCategories("cat1");
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        fund3.setRunning(false);
        fund3.setCategories("cat1");
        funds.add(fund3);

        expectedFunds.add(fund2);
        expectedFunds.add(fund3);
        expectedFunds.add(fund1);

        sortingFundLists sorter = new sortingFundLists();
        funds = sorter.sortFunds(funds, project, false);

        for (int i = 0; i < funds.size(); i++) {
            if (!Objects.equals(funds.get(i).getDeadlines(), expectedFunds.get(i).getDeadlines())) {
                ErrDump += funds.get(i).getDeadlines() + " != " + expectedFunds.get(i).getDeadlines() + "\n";
                passed = false;
            }
        }
        return passed;
    }

}
