
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class test_project extends testTemplate {
    public String ErrDump = new String();

    public boolean test() {
        this.TestName = "project Test";
        if (!test_Constructor()) {
            this.ErrMessage += " Error in Constructor test\n";
            this.ErrMessage += ErrDump;

            return false;
        }

        return true;

    }

    public boolean test_Constructor() {
        boolean passed = true;
        String name = "Example fund";
        String[] tmpstrarr = { "cat1", "cat2", "cat3" };
        ArrayList<String> categories = new ArrayList<String>(Arrays.asList(tmpstrarr));
        String description = "Example description";
        String purpose = "Example purpose";
        String owner = "Example owner";
        String audience = "Example audience";
        long budget = 666;
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusDays(2);
        String activites = "Example activites";
        ArrayList<fundClass> fundlist = new ArrayList<>();
        fundClass fc = new fundClass();
        fc.setTitle("Example fund");
        fc.setDateCreated(LocalDateTime.now());
        fc.setDeadlines(LocalDateTime.now().plusDays(3));
        fc.setCategories("cat1");

        fundlist.add(fc);
        Boolean onlyone = true;

        project pro = new project(name, categories, description, purpose, owner, audience, budget, from, to, activites,
                fundlist, onlyone);
        if (!name.equals(pro.getTitle())) {
            ErrDump += name + " != " + pro.getTitle() + "\n";
            passed = false;
        }
        if (!description.equals(pro.getDescription())) {
            ErrDump += description + " != " + pro.getDescription() + "\n";
            passed = false;
        }
        for (int i = 0; i < categories.size(); i++) {
            if (!Objects.equals(categories.get(i), pro.getCategories().get(i))) {
                ErrDump += categories.get(i) + " != " + pro.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        if (!purpose.equals(pro.getProjectPurpose())) {
            ErrDump += purpose + " != " + pro.getProjectPurpose() + "\n";
            passed = false;
        }
        if (!owner.equals(pro.getProjectOwner())) {
            ErrDump += owner + " != " + pro.getProjectOwner() + "\n";
            passed = false;
        }
        if (!audience.equals(pro.getProjectTargetAudience())) {
            ErrDump += audience + " != " + pro.getProjectTargetAudience() + "\n";
            passed = false;
        }
        if (!Objects.equals(budget, pro.getProjectBudget())) {
            ErrDump += budget + " != " + pro.getProjectBudget() + "\n";
            passed = false;
        }
        if (!Objects.equals(from, pro.getProjectTimeSpanFrom())) {
            ErrDump += from + " != " + pro.getProjectTimeSpanFrom() + "\n";
            passed = false;
        }
        if (!Objects.equals(to, pro.getProjectTimeSpanTo())) {
            ErrDump += to + " != " + pro.getProjectTimeSpanTo() + "\n";
            passed = false;
        }
        if (!Objects.equals(activites, pro.getProjectActivities())) {
            ErrDump += activites + " != " + pro.getProjectActivities() + "\n";
            passed = false;
        }
        System.out.println(fundlist);
        System.out.println(pro.getClosestDeadlineFunds());
        for (int i = 0; i < fundlist.size(); i++) {
            if (!Objects.equals(fundlist.get(i), pro.getClosestDeadlineFunds().get(i))) {
                ErrDump += fundlist.get(i) + " != " + pro.getClosestDeadlineFunds().get(i) + "\n";
                passed = false;
            }
        }

        return passed;
    }

}
