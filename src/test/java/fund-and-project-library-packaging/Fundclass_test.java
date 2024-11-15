import java.time.LocalDateTime;
import java.util.Objects;

public class Fundclass_test extends testTemplate {
    public String ErrDump = new String();

    public boolean test() {
        this.TestName = "FundClass Test";
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
        String description = "Example description";
        long amountFrom = 666;
        long amountTo = 999;
        LocalDateTime[] deadlines = { LocalDateTime.now(), LocalDateTime.now().plusDays(2) };
        String[] categories = { "cat1", "cat2", "cat3" };
        String[] history = { "example history" };
        String[] contacts = { "person:nummer" };
        String website = "aau.dk";
        Boolean collaborated = true;
        Boolean running = false;

        fundClass fc = new fundClass(name, description, amountFrom, amountTo, deadlines, categories, history, contacts,
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
        for (int i = 0; i < deadlines.length; i++) {
            if (!Objects.equals(deadlines[i], fc.getDeadlines().get(i))) {
                ErrDump += deadlines[i] + " != " + fc.getDeadlines().get(i) + "\n";
                passed = false;
            }
        }
        for (int i = 0; i < categories.length; i++) {
            if (!Objects.equals(categories[i], fc.getCategories().get(i))) {
                ErrDump += categories[i] + " != " + fc.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        for(int i = 0; i < history.length; i++){
        if (!Objects.equals(history[i], fc.getCollaborationHistory().get(i))) {
            ErrDump += history[i] + " != " + fc.getCollaborationHistory().get(i) + "\n";
            passed = false;
        }
        }
        
        for(int i = 0; i < contacts.length; i++){
        if (!Objects.equals(contacts[i], fc.getContacts().get(i))) {
            ErrDump += contacts[i] + " != " + fc.getContacts().get(i) + "\n";
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

        return passed;
    }

}
