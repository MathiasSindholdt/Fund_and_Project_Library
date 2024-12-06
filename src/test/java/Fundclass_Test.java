import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class Fundclass_Test extends testTemplate {
    public String ErrDump = new String();

    @Test
    public void test_Constructor() {
        boolean passed = true;
        String name = "Example fund";
        String description = "Example description";
        long amountFrom = 666;
        long amountTo = 999;
        List<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
        deadlines.add(LocalDateTime.now());
        deadlines.add(LocalDateTime.now().plusDays(2));
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

        fundClass fc = new fundClass(name, description, amountFrom, amountTo,
                deadlines, categories, collaborationHistory, contacts,
                website, collaborated, running);
        if (!name.equals(fc.getTitle())) {
            ErrDump += name + " != " + fc.getTitle() + "\n";
            passed = false;
        }
        if (!description.equals(fc.getDescription())) {
            ErrDump += description + " != " + fc.getDescription() + "\n";
            passed = false;
        }
        if (!Objects.equals(amountFrom, fc.getBudgetMin())) {
            ErrDump += amountFrom + " != " + fc.getBudgetMin() + "\n";
            passed = false;
        }
        if (!Objects.equals(amountTo, fc.getBudgetMax())) {
            ErrDump += amountTo + " != " + fc.getBudgetMax() + "\n";
            passed = false;
        }
        for (int i = 0; i < deadlines.size(); i++) {
            if (!Objects.equals(deadlines.get(i), fc.getDeadlines().get(i))) {
                ErrDump += deadlines.get(i) + " != " + fc.getDeadlines().get(i) + "\n";
                passed = false;
            }
        }
        for (int i = 0; i < categories.size(); i++) {
            if (!Objects.equals(categories.get(i), fc.getCategories().get(i))) {
                ErrDump += categories.get(i) + " != " + fc.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        for (int i = 0; i < history.length; i++) {
            if (!Objects.equals(history[i], fc.getCollaborationHistory().get(i))) {
                ErrDump += history[i] + " != " + fc.getCollaborationHistory().get(i) + "\n";
                passed = false;
            }
        }

        for (int i = 0; i < contacts.size(); i++) {
            if (!Objects.equals(contacts.get(i), fc.getContacts().get(i))) {
                ErrDump += contacts.get(i) + " != " + fc.getContacts().get(i) + "\n";
                passed = false;
            }
        }

        if (!website.equals(fc.getFundWebsite())) {
            ErrDump += website + " != " + fc.getFundWebsite() + "\n";
            passed = false;
        }
        if (!Objects.equals(running, fc.getRunning())) {
            ErrDump += running + " != " + fc.getRunning() + "\n";
            passed = false;
        }

    }

}
