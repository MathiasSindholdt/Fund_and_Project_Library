
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;
public class Test_project extends testTemplate {


    @Test
    public void test_Constructor() {
        String name = "Example project";
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
        assertTrue(name.equals(pro.getTitle()));
        assertTrue(description.equals(pro.getDescription()));
        for (int i = 0; i < categories.size(); i++) {
            assertTrue(Objects.equals(categories.get(i), pro.getCategories().get(i)));
        }
        assertTrue(purpose.equals(pro.getProjectPurpose()));
        assertTrue(owner.equals(pro.getProjectOwner()));
        assertTrue(audience.equals(pro.getProjectTargetAudience()));
        assertTrue(Objects.equals(budget, pro.getProjectBudget()));
        assertTrue(Objects.equals(from, pro.getProjectTimeSpanFrom()));
        assertTrue(Objects.equals(to, pro.getProjectTimeSpanTo()));
        assertTrue(Objects.equals(activites, pro.getProjectActivities()));
        for (int i = 0; i < fundlist.size(); i++) {
            assertTrue(Objects.equals(fundlist.get(i), pro.getClosestDeadlineFunds().get(i)));
        }

    }

}
