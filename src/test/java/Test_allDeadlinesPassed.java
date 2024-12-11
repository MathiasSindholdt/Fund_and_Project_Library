import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Test_allDeadlinesPassed {
   
    @Test
    public void Test_allDeadlinesPassed() {
        boolean passed = true;
        String title = "Example fund";
        String description = "Example description";
        long amountFrom = 666;
        long amountTo = 999;
        List<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
        deadlines.add(LocalDateTime.now().minusDays(1));
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Cat1");
        categories.add("Cat2");
        categories.add("Cat3");
        String[] history = { "example history" };
        fundContactClass fcc = new fundContactClass("name", "phone", "email");
        ArrayList<fundContactClass> contacts = new ArrayList<>();
        contacts.add(fcc);
        String website = "aau.dk";
        ArrayList<String> collaborationHistory = new ArrayList<>();
        collaborationHistory.add("exmaple collaborationHistory");
        Boolean collaborated = true;
        Boolean running = false;

        fundClass fc = new fundClass(title, description, amountFrom, amountTo,
                deadlines, categories, collaborationHistory, contacts,
                website, collaborated, running, false);

        assertTrue(fundQSort.allDeadlinesPassed(fc));

        fc.setDeadlines(LocalDateTime.now().plusDays(2));

        assertTrue(!(fundQSort.allDeadlinesPassed(fc)));
    }
}
