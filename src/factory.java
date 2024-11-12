import java.time.LocalDateTime;
import java.util.ArrayList;

public class factory extends projectAbstract {
    // Import project proposal and create a new project
    public project createProjectFromProposal(proposalProject proposal, boolean onlyOneIsNeeded,
            ArrayList<fundClass> fundList) {
        sortingFundLists sorter = new sortingFundLists();
        project newProject = new project();
        for (int i = 0; i < fundList.size(); i++) {
            System.out.println("------------");
            System.out.println(fundList.get(i).getTitle());
            System.out.println("------------");
        }
        newProject.setClosestDeadlineFunds(fundList, onlyOneIsNeeded);
        newProject.setTitle(proposal.getTitle());

        for (String s : proposal.getCategories()) {
            newProject.setCategories(s);
        }
        newProject.setDescription(proposal.getDescription());
        newProject.setProjectPurpose(proposal.getProjectPurpose());
        newProject.setProjectOwner(proposal.getProjectOwner());
        newProject.setProjectTargetAudience(proposal.getProjectTargetAudience());
        newProject.setProjectBudget(proposal.getProjectBudget());
        newProject.setTimeSpan(proposal.getProjectTimeSpanFrom(), proposal.getProjectTimeSpanTo());
        newProject.setProjectActivities(proposal.getProjectActivities());
        return newProject;
    }
}
