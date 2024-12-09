
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class proposalConverter_Test extends testTemplate {

    @Test
    public void test_Constructor() {
        String name = "Example proposal";
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


        proposalProject pro = new proposalProject(name, categories, description,
                purpose, owner, audience, budget, from, to, activites);
        ArrayList<fundClass> fundlist = new ArrayList<>();
        fundClass fc = new fundClass();
        fc.setTitle("Example fund");
        fc.setDateCreated(LocalDateTime.now());
        fc.setDeadlines(LocalDateTime.now().plusDays(3));
        fc.setCategories("cat1");

        fundlist.add(fc);
        factory tmp = new factory();
        project createdPro = tmp.createProjectFromProposal(pro, true, fundlist);

        assertTrue(pro.getTitle().equals(createdPro.getTitle()));
        assertTrue(pro.getDescription().equals(createdPro.getDescription()));
        for (int i = 0; i < categories.size(); i++) {
            assertTrue(Objects.equals(pro.getCategories().get(i), 
                        createdPro.getCategories().get(i)));
        }
        assertTrue(pro.getProjectPurpose().equals(createdPro.getProjectPurpose()));
        assertTrue(pro.getProjectOwner().equals(createdPro.getProjectOwner()));
        assertTrue(pro.getProjectTargetAudience().equals(createdPro.getProjectTargetAudience()));
        assertTrue(Objects.equals(pro.getProjectBudget(), 
                    createdPro.getProjectBudget()));
        assertTrue(Objects.equals(pro.getProjectTimeSpanFrom(), 
                    createdPro.getProjectTimeSpanFrom()));
        assertTrue(Objects.equals(pro.getProjectTimeSpanTo(), 
                    createdPro.getProjectTimeSpanTo()));
        assertTrue(Objects.equals(pro.getProjectActivities(), 
                    createdPro.getProjectActivities()));

    }

}
