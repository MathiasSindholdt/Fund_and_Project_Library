import java.util.ArrayList;

public class archive {
    /*
     * archiveFund() - moves fund to list of archived funds
     *
     */
    public static void archiveFund(fundClass fund, ArrayList<fundClass> fundList,
            ArrayList<fundClass> archivedFunds) {
        fundList.remove(fund);
        archivedFunds.add(fund);
    }

    /*
     * archiveProject() - moves project to list of archived projects
     *
     */
    public static void archiveProject(project project, ArrayList<project> projectList,
            ArrayList<project> archivedProjects) {
        projectList.remove(project);
        archivedProjects.add(project);
    }

    /*
     * archiveProposal() - moves proposal to list of archived proposals
     *
     */
    public static void archiveProposal(proposalProject proposalProject,
            ArrayList<proposalProject> proposalProjectList, ArrayList<proposalProject> archivedProposals) {
        proposalProjectList.remove(proposalProject);
        archivedProposals.add(proposalProject);
    }

}
