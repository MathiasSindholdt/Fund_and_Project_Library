import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class projectcsv_Test extends testTemplate {
    public String ErrDump = new String();

    @Test
    public void test_csvReadWrite() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
        ArrayList<project> projects = new ArrayList<>();
        projects.add(pro);
        ProjectCsvWriter.writeProjectCsv("testcsv.csv", projects);
        ArrayList<project> newpro = ProjectCsvReader.readProjectCsv("testcsv.csv");

        assertTrue(name.equals(newpro.get(0).getTitle()));
        assertTrue(description.equals(newpro.get(0).getDescription()));
        for (int i = 0; i < categories.size(); i++) {
            assertTrue(Objects.equals(categories.get(i), newpro.get(0).getCategories().get(i)));
        }
        assertTrue(purpose.equals(newpro.get(0).getProjectPurpose()));
        assertTrue(owner.equals(newpro.get(0).getProjectOwner()));
        assertTrue(audience.equals(newpro.get(0).getProjectTargetAudience()));
        assertTrue(Objects.equals(budget, newpro.get(0).getProjectBudget()));
        assertEquals(formatter.format(from), formatter.format(newpro.get(0).getProjectTimeSpanFrom()));
        assertEquals(formatter.format(to), formatter.format(newpro.get(0).getProjectTimeSpanTo()));
        assertEquals(activites, newpro.get(0).getProjectActivities());
        if (newpro.get(0).getClosestDeadlineFunds().size() > 0) {
            for (int i = 0; i < fundlist.size(); i++) {
                assertEquals(fundlist.get(i), newpro.get(0).getClosestDeadlineFunds().get(i));
            }
        }

    }

}
