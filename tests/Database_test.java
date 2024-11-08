import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Database_test extends testTemplate {
    public String ErrDump = new String();

    public boolean test() {
        this.TestName = "Database Test";
        boolean passed = true;
        fundClass expectedFundClass = Construct_fund(1);
        proposalProject expectedProposal = Construct_proposal();
        project expectedProject = Construct_project();
        Database db = new Database();
        db.AddFundToDatabase(expectedFundClass);
        db.addProjectToDatabase(expectedProject);
        db.addProjectProposalToDatabase(expectedProposal);
        db.AddArchivedFundToDatabase(expectedFundClass);
        db.addArchivedProjectToDatabase(expectedProject);
        db.addArchivedProjectProposalToDatabase(expectedProposal);
        ArrayList<fundClass> fundlist = new ArrayList<>();
        ArrayList<proposalProject> proposallist = new ArrayList<>();
        ArrayList<project> projectlist = new ArrayList<>();
        fundlist = db.getAllFunds();
        projectlist = db.getAllProjects();
        proposallist = db.getAllProjectProposals();
        if (!compare_Funds(expectedFundClass, fundlist.get(fundlist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing funds test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }

        System.out.println(projectlist);
        if (!compare_proposalProjects(expectedProposal, proposallist.get(proposallist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing proposals test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }
        if (!compare_projects(expectedProject, projectlist.get(projectlist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing projects test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }

        fundlist = db.getAllArchivedFunds();
        projectlist = db.getAllArchivedProjects();
        proposallist = db.getAllArchivedProjectProposals();
        if (!compare_Funds(expectedFundClass, fundlist.get(fundlist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing archivedfunds test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }
        if (!compare_proposalProjects(expectedProposal, proposallist.get(proposallist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing archivedProposals test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }
        if (!compare_projects(expectedProject, projectlist.get(projectlist.size() - 1))) {
            this.ErrMessage += "\n\n Error while comparing archivedProjects test\n";
            this.ErrMessage += ErrDump;

            passed = false;
        }

        return passed;

    }

    public proposalProject Construct_proposal() {

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

        proposalProject project = new proposalProject(name, categories, description, purpose, owner, audience, budget,
                from, to, activites);

        return project;
    }

    public project Construct_project() {

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

        project project = new project(name, categories, description, purpose, owner, audience, budget, from, to,
                activites,
                fundlist, onlyone);

        return project;
    }

    public fundClass Construct_fund(int fund_number) {
        String name = "Example fund" + fund_number;
        String description = "Example description" + fund_number;
        long amountFrom = 666;
        long amountTo = 999;
        LocalDateTime[] deadlines = { LocalDateTime.now(), LocalDateTime.now().plusDays(2) };
        String[] categories = { "cat1", "cat2", "cat3" };
        String[] history = { "example history" + fund_number };
        String[] contacts = { "person:nummer" + fund_number };
        String website = "aau.dk";
        Boolean collaborated = true;
        Boolean running = false;

        fundClass fc = new fundClass(name, description, amountFrom, amountTo, deadlines, categories, history, contacts,
                website, collaborated, running);

        return fc;
    }

    private boolean compare_Funds(fundClass fund1, fundClass fund2) {
        boolean passed = true;
        if (!Objects.equals(fund1.getTitle(), fund2.getTitle())) {
            ErrDump += fund1.getTitle() + " != " + fund2.getTitle() + "\n";
            passed = false;
        }
        if (!Objects.equals(fund1.getDescription(), fund2.getDescription())) {
            ErrDump += fund1.getDescription() + " != " + fund2.getDescription() + "\n";
            passed = false;
        }
        if (!Objects.equals(fund1.getBudgetMin(), fund2.getBudgetMin())) {
            ErrDump += fund1.getBudgetMin() + " != " + fund2.getBudgetMin() + "\n";
            passed = false;
        }
        if (!Objects.equals(fund1.getBudgetMax(), fund2.getBudgetMax())) {
            ErrDump += fund1.getBudgetMax() + " != " + fund2.getBudgetMax() + "\n";
            passed = false;
        }
        for (int i = 0; i < fund1.getDeadlines().size() && i < fund2.getDeadlines().size(); i++) {
            if (!Objects.equals(fund1.getDeadlines().get(i), fund2.getDeadlines().get(i))) {
                ErrDump += fund1.getDeadlines().get(i) + " != " + fund2.getDeadlines().get(i) + "\n";
                passed = false;
            }
        }
        for (int i = 0; i < fund1.getCategories().size() && i < fund2.getCategories().size(); i++) {
            if (!Objects.equals(fund1.getCategories().get(i).trim(), fund2.getCategories().get(i).trim())) {
                ErrDump += fund1.getCategories().get(i) + " != " + fund2.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        for (int i = 0; i < fund1.getCollaborationHistory().size() && i < fund2.getCollaborationHistory().size(); i++) {
            if (!Objects.equals(fund1.getCollaborationHistory().get(i), fund2.getCollaborationHistory().get(i))) {
                ErrDump += fund1.getCollaborationHistory().get(i) + " != " + fund2.getCollaborationHistory().get(i)
                        + "\n";
                passed = false;
            }
        }

        for (int i = 0; i < fund1.getContacts().size() && i < fund2.getContacts().size(); i++) {
            if (!Objects.equals(fund1.getContacts().get(i), fund2.getContacts().get(i))) {
                ErrDump += fund1.getContacts().get(i) + " != " + fund2.getContacts().get(i) + "\n";
                passed = false;
            }
        }

        if (!Objects.equals(fund1.getFundWebsite(), fund2.getFundWebsite())) {
            ErrDump += fund1.getFundWebsite() + " != " + fund2.getFundWebsite() + "\n";
            passed = false;
        }
        if (!Objects.equals(fund1.getRunning(), fund2.getRunning())) {
            ErrDump += fund1.getRunning() + " != " + fund2.getRunning() + "\n";
            passed = false;
        }

        return passed;
    }

    private boolean compare_proposalProjects(proposalProject project1, proposalProject project2) {
        boolean passed = true;
        if (!Objects.equals(project1.getTitle(), project2.getTitle())) {
            ErrDump += project1.getTitle() + " != " + project2.getTitle() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getDescription(), project2.getDescription())) {
            ErrDump += project1.getDescription() + " != " + project2.getDescription() + "\n";
            passed = false;
        }
        for (int i = 0; i < project1.getCategories().size() && i < project2.getCategories().size(); i++) {
            if (!Objects.equals(project1.getCategories().get(i).trim(), project2.getCategories().get(i).trim())) {
                ErrDump += project1.getCategories().get(i) + " != " + project2.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        if (!Objects.equals(project1.getProjectPurpose(), project2.getProjectPurpose())) {
            ErrDump += project1.getProjectPurpose() + " != " + project2.getProjectPurpose() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectOwner(), project2.getProjectOwner())) {
            ErrDump += project1.getProjectOwner() + " != " + project2.getProjectOwner() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTargetAudience(), project2.getProjectTargetAudience())) {
            ErrDump += project1.getProjectTargetAudience() + " != " + project2.getProjectTargetAudience() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectBudget(), project2.getProjectBudget())) {
            ErrDump += project1.getProjectBudget() + " != " + project2.getProjectBudget() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTimeSpanFrom(), project2.getProjectTimeSpanFrom())) {
            ErrDump += project1.getProjectTimeSpanFrom() + " != " + project2.getProjectTimeSpanFrom() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTimeSpanTo(), project2.getProjectTimeSpanTo())) {
            ErrDump += project1.getProjectTimeSpanTo() + " != " + project2.getProjectTimeSpanTo() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectActivities(), project2.getProjectActivities())) {
            ErrDump += project1.getProjectActivities() + " != " + project2.getProjectActivities() + "\n";
            passed = false;
        }
        return passed;
    }

    private boolean compare_projects(project project1, project project2) {
        boolean passed = true;
        if (!Objects.equals(project1.getTitle(), project2.getTitle())) {
            ErrDump += project1.getTitle() + " != " + project2.getTitle() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getDescription(), project2.getDescription())) {
            ErrDump += project1.getDescription() + " != " + project2.getDescription() + "\n";
            passed = false;
        }
        for (int i = 0; i < project1.getCategories().size() && i < project2.getCategories().size(); i++) {
            if (!Objects.equals(project1.getCategories().get(i).trim(), project2.getCategories().get(i).trim())) {
                ErrDump += project1.getCategories().get(i) + " != " + project2.getCategories().get(i) + "\n";
                passed = false;
            }
        }
        if (!Objects.equals(project1.getProjectPurpose(), project2.getProjectPurpose())) {
            ErrDump += project1.getProjectPurpose() + " != " + project2.getProjectPurpose() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectOwner(), project2.getProjectOwner())) {
            ErrDump += project1.getProjectOwner() + " != " + project2.getProjectOwner() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTargetAudience(), project2.getProjectTargetAudience())) {
            ErrDump += project1.getProjectTargetAudience() + " != " + project2.getProjectTargetAudience() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectBudget(), project2.getProjectBudget())) {
            ErrDump += project1.getProjectBudget() + " != " + project2.getProjectBudget() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTimeSpanFrom(), project2.getProjectTimeSpanFrom())) {
            ErrDump += project1.getProjectTimeSpanFrom() + " != " + project2.getProjectTimeSpanFrom() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectTimeSpanTo(), project2.getProjectTimeSpanTo())) {
            ErrDump += project1.getProjectTimeSpanTo() + " != " + project2.getProjectTimeSpanTo() + "\n";
            passed = false;
        }
        if (!Objects.equals(project1.getProjectActivities(), project2.getProjectActivities())) {
            ErrDump += project1.getProjectActivities() + " != " + project2.getProjectActivities() + "\n";
            passed = false;
        }
        for (int i = 0; i < project1.getClosestDeadlineFunds().size()
                && i < project2.getClosestDeadlineFunds().size(); i++) {
            if (!Objects.equals(project1.getClosestDeadlineFunds().get(i), project2.getClosestDeadlineFunds().get(i))) {
                ErrDump += project1.getClosestDeadlineFunds().get(i) + " != "
                        + project2.getClosestDeadlineFunds().get(i) + "\n";
                passed = false;
            }
        }
        return passed;
    }
}
