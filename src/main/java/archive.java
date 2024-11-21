
public class archive {
    /*
     * archiveFund() - moves fund to list of archived funds
     *
     */
    public static void archiveFund(fundClass fund) {
        main.fundList.remove(fund);
        main.archiveFundList.add(fund);
    }

    /*
     * archiveProject() - moves project to list of archived projects
     *
     */
    public static void archiveProject(project project) {
        main.projectList.remove(project);
        main.archiveProjectList.add(project);
    }

    /*
     * archiveProposal() - moves proposal to list of archived proposals
     *
     */
    public static void archiveProposal(proposalProject proposalProject) {
        main.proposalList.remove(proposalProject);
        main.deniedProposalList.add(proposalProject);
    }

}
