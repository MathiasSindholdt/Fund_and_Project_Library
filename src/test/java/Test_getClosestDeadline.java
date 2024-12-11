
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class Test_getClosestDeadline extends testTemplate {


    @Test
    public void test_getClosestDeadline() {
        LocalDateTime testDate1 = LocalDateTime.now().minusDays(1);
        LocalDateTime testDate2 = LocalDateTime.now().plusDays(1);
        LocalDateTime testDate3 = LocalDateTime.now().plusHours(1);

        boolean passed = true;
        String title = "Example fund";
        String description = "Example description";
        long amountFrom = 666;
        long amountTo = 999;
        List<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
        deadlines.add(testDate1);
        deadlines.add(testDate2);

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


        assertEquals(fundQSort.getClosestDeadline(fc),testDate2);

        fc.setDeadlines(testDate3);

        assertEquals(fundQSort.getClosestDeadline(fc),testDate3);
    }

}
